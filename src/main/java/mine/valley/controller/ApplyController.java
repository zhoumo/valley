package mine.valley.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mine.valley.base.BaseController;
import mine.valley.constant.ApplyType;
import mine.valley.entity.Apply;
import mine.valley.entity.User;
import mine.valley.service.ApplyService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplyController extends BaseController {

	@Autowired
	private ApplyService applyService;

	@RequestMapping("/apply.do")
	public String apply(String applyType, String resume, String selectedJobs, HttpServletRequest request) {
		String jobIds = "";
		JSONObject jobs = new JSONObject(selectedJobs);
		for (Object key : jobs.keySet()) {
			JSONObject job = jobs.getJSONObject(key.toString());
			jobIds += job.getString("id") + ",";
		}
		User user = (User) request.getSession().getAttribute(super.getUserName());
		if (ApplyType.CREATOR.getName().equals(applyType)) {
			applyService.applyCreator(jobIds, user, resume);
		} else {
			applyService.applyAuditor(jobIds, user, resume);
		}
		return "redirect:/";
	}

	@RequestMapping("/approve.do")
	public String approve(Short type, Long user, Boolean approved) {
		List<Apply> applyList = applyService.getApplyList(type, user);
		for (Apply apply : applyList) {
			apply.setApproved(approved);
		}
		applyService.batchSave(applyList);
		return "redirect:/#?active=user";
	}
}
