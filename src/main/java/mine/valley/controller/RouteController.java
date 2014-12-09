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

	@RequestMapping("home/paper/create")
	public String homePaperCreate() {
		return "home/module/paper/create";
	}

	@RequestMapping("home/paper/audit")
	public String homePaperAudit() {
		return "home/module/paper/audit";
	}

	@RequestMapping("home/paper/show")
	public String homePaperShow() {
		return "home/module/paper/show";
	}

	@RequestMapping("home/paper/exam")
	public String homePaperExam() {
		return "home/module/paper/exam";
	}

	@RequestMapping("home/paper/list")
	public String homePaperList() {
		return "home/module/paper/list";
	}

	@RequestMapping("home/account/edit")
	public String homeAccountEdit() {
		return "home/module/account/edit";
	}
}
