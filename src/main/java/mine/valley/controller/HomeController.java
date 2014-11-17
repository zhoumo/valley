package mine.valley.controller;

import mine.valley.base.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController {

	@RequestMapping("home/default")
	public String homeDefault() {
		return "home/default";
	}

	@RequestMapping("home/my-exam")
	public String homeMyExam() {
		return "home/my-exam";
	}

	@RequestMapping("home/system-message")
	public String homeSystemMessage() {
		return "home/system-message";
	}

	@RequestMapping("home/apply-producer")
	public String homeApplyProducer() {
		return "home/apply-producer";
	}

	@RequestMapping("home/apply-auditor")
	public String homeApplyAuditor() {
		return "home/apply-auditor";
	}

	@RequestMapping("home/produce")
	public String homeProduce() {
		return "home/produce";
	}

	@RequestMapping("home/audit")
	public String homeAudit() {
		return "home/audit";
	}

	@RequestMapping("home/account")
	public String homeAccount() {
		return "home/account";
	}
}
