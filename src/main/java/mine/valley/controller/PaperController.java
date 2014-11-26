package mine.valley.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mine.valley.base.BaseController;
import mine.valley.entity.Paper;
import mine.valley.entity.Question;
import mine.valley.entity.User;
import mine.valley.service.PaperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaperController extends BaseController {

	@Autowired
	private PaperService paperService;

	@RequestMapping("/savePaper.do")
	public String savePaper(String paperName, HttpServletRequest request) {
		Paper paper = new Paper();
		List<Question> questionList = new ArrayList<Question>();
		questionList.addAll(paperService.convertQuestionList(request.getParameter("singleSelections"), Question.TYPE_SINGLE_SELECTIONS, paper));
		questionList.addAll(paperService.convertQuestionList(request.getParameter("multipleSelections"), Question.TYPE_MULTIPLE_SELECTIONS, paper));
		questionList.addAll(paperService.convertQuestionList(request.getParameter("essayQuestions"), Question.TYPE_ESSAY_QUESTIONS, paper));
		paper.setPaperName(paperName);
		paper.setQuestions(questionList);
		paper.setAuthor((User) request.getSession().getAttribute(super.getUserName()));
		paper.setCreateTime();
		paperService.savePaper(paper);
		return "redirect:/";
	}
}
