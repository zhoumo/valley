package mine.valley.controller;

import javax.servlet.http.HttpSession;

import mine.valley.base.BaseController;
import mine.valley.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController extends BaseController {

	@RequestMapping("/")
	public String index(HttpSession session) {
		User user = (User) session.getAttribute(super.getUserName());
		if (user.getType().equals(User.USER_TYPE_ADMIN)) {
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

	@RequestMapping("home/create-paper")
	public String homeCreatePaper() {
		return "home/module/create-paper";
	}

	@RequestMapping("home/audit-paper")
	public String homeAuditPaper() {
		return "home/module/audit-paper";
	}

	@RequestMapping("home/exam")
	public String homeExam() {
		return "home/module/exam";
	}

	@RequestMapping("home/paper-list")
	public String homePaperList() {
		return "home/module/paper-list";
	}

	@RequestMapping("home/edit-user")
	public String homeEditUser() {
		return "home/module/edit-user";
	}
}
