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
	public String savePaper(Paper paper, HttpServletRequest request) {
		List<Question> questionList = new ArrayList<Question>();
		questionList.addAll(paperService.createSingleSelectionList(request.getParameter("singleSelections"), paper));
		questionList.addAll(paperService.createMultipleSelectionList(request.getParameter("multipleSelections"), paper));
		questionList.addAll(paperService.createEssayQuestionList(request.getParameter("essayQuestions"), paper));
		paper.setQuestions(questionList);
		paper.setAuthor((User) request.getSession().getAttribute(super.getUserName()));
		paper.setCreateTime();
		paperService.savePaper(paper);
		return "redirect:/";
	}
}
