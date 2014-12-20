package mine.valley.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mine.valley.base.BaseController;
import mine.valley.constant.ExamStatus;
import mine.valley.constant.PaperStatus;
import mine.valley.entity.Exam;
import mine.valley.entity.Paper;
import mine.valley.entity.Question;
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
		paper.setAuthor(getUser());
		paper.setJob(jobService.getJob(paper.getSelectJobId()));
		paper.setStatus(PaperStatus.CREATE.getValue());
		paper.setCreateTime();
		paperService.save(paper);
		return PAPER_LIST_FOR_CREATE;
	}

	@RequestMapping("/submitPaper.do")
	public String submitPaper(Long id) {
		Paper paper = paperService.getPaper(id);
		paper.setStatus(PaperStatus.SUBMIT.getValue());
		paperService.save(paper);
		return PAPER_LIST_FOR_CREATE;
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
		paper.setStatus(isAuditYes ? PaperStatus.AUDIT_YES.getValue() : PaperStatus.AUDIT_NO.getValue());
		paper.setAuditor(getUser());
		paper.setAuditTime(new Date());
		paperService.save(paper);
		return PAPER_LIST_FOR_AUDITED;
	}

	@RequestMapping("/renewPaper.do")
	public String renewPaper(Long id) {
		Paper paper = paperService.getPaper(id);
		paper.setStatus(PaperStatus.CREATE.getValue());
		paperService.save(paper);
		return PAPER_RENEW + id;
	}

	@RequestMapping("/reviewPaper.do")
	public String reviewPaper(Long id) {
		Paper paper = paperService.getPaper(id);
		paper.setStatus(PaperStatus.SUBMIT.getValue());
		paperService.save(paper);
		return ACTIVE_PAPER;
	}

	@RequestMapping("/deletePaper.do")
	public String deletePaper(Long id) {
		Paper paper = paperService.getPaper(id);
		paperService.delete(paper);
		return ACTIVE_PAPER;
	}

	@RequestMapping("/getPaperList.do")
	@ResponseBody
	public Page<Paper> getPaperList(String type, Page<Paper> page, String job, String author) {
		if ("create".equals(type)) {
			page = paperService.getPaperByAuthor(page, getUser().getId());
		} else if ("audit".equals(type)) {
			page = paperService.getNoAuditPaper(page, getUser().getId());
		} else if ("audited".equals(type)) {
			page = paperService.getAuditedPaper(page, getUser().getId());
		} else if ("exam".equals(type)) {
			page = paperService.getExamPaper(page, getUser().getId());
		} else {
			page = paperService.getSubmitPaper(page, transcode(job), transcode(author));
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
		exam.setStatus(ExamStatus.END.getValue());
		examService.save(exam);
		return ROOT_PATH;
	}

	@RequestMapping("/startExam.do")
	@ResponseBody
	public Long startExam(Long paperId) {
		return examService.startExam(paperService.getPaper(paperId), getUser());
	}

	@RequestMapping("/timer.do")
	@ResponseBody
	public int timer(Long paperId) {
		return examService.getTime(paperId + "-" + getUser().getId());
	}
}
