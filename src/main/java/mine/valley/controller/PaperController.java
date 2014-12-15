package mine.valley.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mine.valley.base.BaseController;
import mine.valley.constant.PaperStatus;
import mine.valley.entity.Exam;
import mine.valley.entity.Paper;
import mine.valley.entity.Question;
import mine.valley.entity.User;
import mine.valley.model.Page;
import mine.valley.service.ExamService;
import mine.valley.service.JobService;
import mine.valley.service.PaperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaperController extends BaseController {

	@Autowired
	private PaperService paperService;

	@Autowired
	private ExamService examService;

	@Autowired
	private JobService jobService;

	@RequestMapping("/savePaper.do")
	public String savePaper(Paper paper, HttpServletRequest request) {
		List<Question> questionList = new ArrayList<Question>();
		questionList.addAll(paperService.createSingleSelectionList(request.getParameter("singleSelections"), paper));
		questionList.addAll(paperService.createMultipleSelectionList(request.getParameter("multipleSelections"), paper));
		questionList.addAll(paperService.createEssayQuestionList(request.getParameter("essayQuestions"), paper));
		paper.setQuestions(questionList);
		paper.setAuthor((User) request.getSession().getAttribute(super.getUserName()));
		paper.setJob(jobService.getJob(paper.getSelectJobId()));
		paper.setStatus(PaperStatus.CREATE.getValue());
		paper.setCreateTime();
		paperService.savePaper(paper);
		return "redirect:/#/paper/list?type=create";
	}

	@RequestMapping("/submitPaper.do")
	public String submitPaper(Long id) {
		Paper paper = paperService.getPaper(id);
		paper.setStatus(PaperStatus.SUBMIT.getValue());
		paperService.savePaper(paper);
		return "redirect:/#/paper/list?type=create";
	}

	@RequestMapping("/auditPaper.do")
	public String auditPaper(Long id, HttpServletRequest request) {
		Paper paper = paperService.getPaper(id);
		Boolean isAuditYes = true;
		for (Question question : paper.getQuestions()) {
			String auditResult = request.getParameter("audit" + question.getId());
			if ("true".equalsIgnoreCase(auditResult)) {
				question.setAudit(true);
			} else {
				isAuditYes = false;
				question.setAudit(false);
				question.setComment(auditResult);
			}
		}
		if (isAuditYes) {
			paper.setStatus(PaperStatus.AUDIT_YES.getValue());
		} else {
			paper.setStatus(PaperStatus.AUDIT_NO.getValue());
		}
		paper.setAuditor((User) request.getSession().getAttribute(super.getUserName()));
		paper.setAuditTime(new Date());
		paperService.savePaper(paper);
		return "redirect:/#/paper/list?type=audit";
	}

	@RequestMapping("/renewPaper.do")
	public String renewPaper(Long id) {
		Paper paper = paperService.getPaper(id);
		paper.setStatus(PaperStatus.CREATE.getValue());
		paperService.savePaper(paper);
		return "redirect:/#/paper/create?id=" + id;
	}

	@RequestMapping("/reviewPaper.do")
	public String reviewPaper(Long id) {
		Paper paper = paperService.getPaper(id);
		paper.setStatus(PaperStatus.SUBMIT.getValue());
		paperService.savePaper(paper);
		return "redirect:/#?active=paper";
	}

	@RequestMapping("/deletePaper.do")
	public String deletePaper(Long id) {
		Paper paper = paperService.getPaper(id);
		paperService.deletePaper(paper);
		return "redirect:/#?active=paper";
	}

	@RequestMapping("/getPaperList.do")
	@ResponseBody
	public Page<Paper> getPaperList(String type, Page<Paper> page, HttpSession session) {
		User user = (User) session.getAttribute(super.getUserName());
		if ("create".equals(type)) {
			page = paperService.getPaperByAuthor(page, user.getId());
		} else if ("audit".equals(type)) {
			page = paperService.getNoAuditPaper(page, user.getId());
		} else if ("exam".equals(type)) {
			page = paperService.getExamPaper(page, user.getId());
		} else {
			page = paperService.getSubmitPaper(page);
		}
		return page;
	}

	@RequestMapping("/getPaper.do")
	@ResponseBody
	public Paper getPaperList(Long id) {
		return paperService.getPaper(id);
	}

	@RequestMapping("/finishExam.do")
	public String finishExam(Long id, HttpServletRequest request) {
		Exam exam = examService.getExam(id);
		Map<String, String> answer = new HashMap<String, String>();
		for (Object key : request.getParameterMap().keySet()) {
			if (!key.toString().contains("_")) {
				continue;
			}
			String questionId = key.toString().split("_")[1];
			answer.put(questionId, request.getParameter(key.toString()));
		}
		exam.setAnswer(answer.toString());
		examService.saveExam(exam);
		return "redirect:/";
	}

	@RequestMapping("/startExam.do")
	@ResponseBody
	public Long startExam(Long paperId, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(super.getUserName());
		Paper paper = paperService.getPaper(paperId);
		return examService.startExam(paper, user);
	}

	@RequestMapping("/timer.do")
	@ResponseBody
	public int timer(Long paperId, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(super.getUserName());
		return examService.getTime(paperId + "-" + user.getId());
	}
}
