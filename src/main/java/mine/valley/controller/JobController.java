package mine.valley.controller;

import java.io.IOException;
import java.util.List;

import mine.valley.base.BaseController;
import mine.valley.entity.Job;
import mine.valley.model.Tree;
import mine.valley.service.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JobController extends BaseController {

	@Autowired
	private JobService jobService;

	@RequestMapping("/getJobList.do")
	@ResponseBody
	public List<Job> getJobList() {
		return jobService.getJobList();
	}

	@RequestMapping("/getApplyJobList.do")
	@ResponseBody
	public List<Job> getApplyJobList() {
		return jobService.getApplyJobList(getUser());
	}

	@RequestMapping("/getJobTree.do")
	@ResponseBody
	public List<Tree> getJobTree() throws IOException {
		return jobService.getJobTree();
	}

	@RequestMapping("/saveJob.do")
	public String saveJob(Job job) {
		jobService.save(job);
		return ACTIVE_JOB;
	}

	@RequestMapping("/deleteJob.do")
	public String deleteJob(Long id) {
		jobService.delete(id);
		return ACTIVE_JOB;
	}
}
