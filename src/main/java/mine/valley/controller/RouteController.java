package mine.valley.controller;

import mine.valley.base.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController extends BaseController {

	@RequestMapping("ueditor/controller")
	public String ueditorController() {
		return "ueditor/controller";
	}

	@RequestMapping("home/index")
	public String homeIndex() {
		return "home/index";
	}

	@RequestMapping("home/apply-auditor")
	public String homeApplyAuditor() {
		return "home/module/apply-auditor";
	}

	@RequestMapping("home/apply-producer")
	public String homeApplyProducer() {
		return "home/module/apply-producer";
	}

	@RequestMapping("home/audited-paper-list")
	public String homeAuditedPaperList() {
		return "home/module/audited-paper-list";
	}

	@RequestMapping("home/create-paper")
	public String homeCreatePaper() {
		return "home/module/create-paper";
	}

	@RequestMapping("home/edit-user-info")
	public String homeEditUserInfo() {
		return "home/module/edit-user-info";
	}

	@RequestMapping("home/my-paper-list")
	public String homeMyPaperList() {
		return "home/module/my-paper-list";
	}
}
