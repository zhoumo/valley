package mine.valley.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mine.valley.base.BaseController;

@Controller
public class PaperController extends BaseController {

	@RequestMapping("/savePaper.do")
	public String savePaper(HttpServletRequest request) {
		System.out.println(request.getParameter("singleSelections"));
		System.out.println(request.getParameter("multipleSelections"));
		System.out.println(request.getParameter("essayQuestions"));
		return "redirect:/";
	}
}
