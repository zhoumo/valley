package mine.valley.controller;

import mine.valley.base.BaseController;
import mine.valley.constant.ApplyType;
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
	public String apply(String applyType, String resume, String selectedJobs) {
		String jobIds = "";
		JSONObject jobs = new JSONObject(selectedJobs);
		for (Object key : jobs.keySet()) {
			JSONObject job = jobs.getJSONObject(key.toString());
			jobIds += job.getString("id") + ",";
		}
		if (ApplyType.CREATOR.getName().equals(applyType)) {
			applyService.applyCreator(jobIds, getUser(), resume);
		} else {
			applyService.applyAuditor(jobIds, getUser(), resume);
		}
		return ROOT_PATH;
	}

	@RequestMapping("/approve.do")
	public String approve(Short type, Long user, Boolean approved) {
		applyService.approve(type, user, approved);
		return ACTIVE_USER;
	}
}
