package mine.valley.service;

import java.util.ArrayList;
import java.util.List;

import mine.valley.base.BaseService;
import mine.valley.entity.Job;
import mine.valley.model.Tree;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobService extends BaseService {

	public List<Job> getJobList() {
		return baseDao.find("FROM Job WHERE isDeleted = false");
	}

	public List<Tree> getJobTree() {
		List<Tree> treeList = new ArrayList<Tree>();
		for (Job job : getJobList()) {
			if (job.getParent() == null) {
				treeList.add(buildJobTree(job));
			}
		}
		return treeList;
	}

	private Tree buildJobTree(Job job) {
		Tree tree = new Tree(job.getJobName(), job.getId());
		tree.setParent(job.getParent() == null ? null : job.getParent().getId());
		if (job.getChildren() == null) {
			return tree;
		}
		List<Tree> treeList = new ArrayList<Tree>();
		for (Job childJob : job.getChildren()) {
			if (childJob.getIsDeleted() == false) {
				treeList.add(buildJobTree(childJob));
			}
		}
		tree.setNodes(treeList);
		return tree;
	}

	public void saveJob(Job job) {
		String jobName = job.getJobName();
		Long jobParentId = job.getParentId();
		if (job.getId() != null) {
			job = baseDao.get(Job.class, job.getId());
			job.setJobName(jobName);
		} else {
			job.setCreateTime();
		}
		if (jobParentId != null && jobParentId != job.getId()) {
			job.setParent(baseDao.get(Job.class, jobParentId));
			job.setJobLevel(job.getParent().getJobLevel() + 1);
		} else {
			job.setParent(null);
			job.setJobLevel(0);
		}
		baseDao.save(job);
	}

	public void deleteJob(Long id) {
		Job job = baseDao.get(Job.class, id);
		job.setIsDeleted(true);
		baseDao.save(job);
	}
}
