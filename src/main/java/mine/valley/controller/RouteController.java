package mine.valley.controller;

import javax.servlet.http.HttpSession;

import mine.valley.base.BaseController;
import mine.valley.constant.RoleType;
import mine.valley.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController extends BaseController {

	@RequestMapping("/")
	public String index(HttpSession session) {
		User user = (User) session.getAttribute(super.getUserName());
		if (user.getType().equals(RoleType.ADMIN.getValue())) {
			return "admin";
		} else {
			return "home";
		}
	}

	@RequestMapping("ueditor/controller")
	public String ueditorController() {
		return "ueditor/controller";
	}

	@RequestMapping("home/index")
	public String homeIndex() {
		return "home/index";
	}

	@RequestMapping("home/create")
	public String homeCreate() {
		return "home/module/create";
	}

	@RequestMapping("home/audit")
	public String homeAudit() {
		return "home/module/audit";
	}

	@RequestMapping("home/exam")
	public String homeExam() {
		return "home/module/exam";
	}

	@RequestMapping("home/papers")
	public String homePapers() {
		return "home/module/papers";
	}

	@RequestMapping("home/account")
	public String homeAccount() {
		return "home/module/account";
	}
}
