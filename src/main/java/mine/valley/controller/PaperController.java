package mine.valley.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mine.valley.base.BaseController;
import mine.valley.entity.Paper;
import mine.valley.entity.Question;
import mine.valley.entity.User;
import mine.valley.model.Page;
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
		paper.setCreateTime();
		paperService.savePaper(paper);
		return "redirect:/";
	}

	@RequestMapping("/getPaperList.do")
	@ResponseBody
	public Page<Paper> getPaperList(String type, Page<Paper> page) {
		return paperService.getAllPaper(page);
	}

	@RequestMapping("/getPaper.do")
	@ResponseBody
	public Paper getPaperList(Long id) {
		return paperService.getPaper(id);
	}
}
