package mine.valley.service;

import java.util.List;

import mine.valley.base.BaseService;
import mine.valley.constant.ApplyType;
import mine.valley.entity.Apply;
import mine.valley.entity.Job;
import mine.valley.entity.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class ApplyService extends BaseService {

	private void save(Apply apply) {
		baseDao.save(apply);
	}

	private void deleteByUser(Long uesrId, Short type) {
		baseDao.executeSQL("DELETE FROM APPLY WHERE USER_ID = " + uesrId + " AND TYPE = " + type);
	}

	public void batchSave(List<Apply> applyList) {
		for (Apply apply : applyList) {
			baseDao.save(apply);
		}
	}

	private void apply(String jobIds, User user, String resume, Short type) {
		deleteByUser(user.getId(), type);
		for (String jobId : jobIds.split(",")) {
			if (StringUtils.isEmpty(jobId)) {
				continue;
			}
			Apply apply = new Apply();
			apply.setUser(user);
			apply.setJob(baseDao.get(Job.class, Long.parseLong(jobId)));
			apply.setResume(resume);
			apply.setType(type);
			apply.setCreateTime();
			save(apply);
		}
	}

	public void applyCreator(String jobIds, User user, String resume) {
		apply(jobIds, user, resume, ApplyType.CREATOR.getValue());
	}

	public void applyAuditor(String jobIds, User user, String resume) {
		apply(jobIds, user, resume, ApplyType.AUDITOR.getValue());
	}

	public List<Apply> getApplyList(Short type, Long userId) {
		return baseDao.find("FROM Apply WHERE type = ? AND user.id = ?", type, userId);
	}

	public boolean isApplied(Short type, Long userId) {
		return getApplyList(type, userId).size() != 0;
	}

	public boolean isApproved(Short type, Long userId) {
		return baseDao.find("FROM Apply WHERE type = ? AND user.id = ? AND approved = TRUE", type, userId).size() != 0;
	}
}
