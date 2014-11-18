package mine.valley.controller;

import mine.valley.base.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController extends BaseController {

	@RequestMapping("admin/index")
	public String adminIndex() {
		return "admin/index";
	}

	@RequestMapping("admin/job")
	public String adminJob() {
		return "admin/module/job";
	}

	@RequestMapping("admin/user")
	public String adminUser() {
		return "admin/module/user";
	}

	@RequestMapping("admin/paper")
	public String adminPaper() {
		return "admin/module/paper";
	}

	@RequestMapping("home/index")
	public String homeIndex() {
		return "home/index";
	}

	@RequestMapping("home/my-exam")
	public String homeMyExam() {
		return "home/module/my-exam";
	}

	@RequestMapping("home/system-message")
	public String homeSystemMessage() {
		return "home/module/system-message";
	}

	@RequestMapping("home/apply-producer")
	public String homeApplyProducer() {
		return "home/module/apply-producer";
	}

	@RequestMapping("home/apply-auditor")
	public String homeApplyAuditor() {
		return "home/module/apply-auditor";
	}

	@RequestMapping("home/produce")
	public String homeProduce() {
		return "home/module/produce";
	}

	@RequestMapping("home/audit")
	public String homeAudit() {
		return "home/module/audit";
	}

	@RequestMapping("home/account")
	public String homeAccount() {
		return "home/module/account";
	}
}
