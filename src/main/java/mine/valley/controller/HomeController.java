package mine.valley.controller;

import mine.valley.base.BaseController;
import mine.valley.entity.Job;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController {

	@RequestMapping("home/default")
	public String homeDefault(Job job) {
		return "home/default";
	}
}
