package mine.valley.service;

import java.util.Arrays;
import java.util.List;

import mine.valley.base.BaseService;
import mine.valley.constant.ApplyType;
import mine.valley.constant.RoleType;
import mine.valley.entity.Role;
import mine.valley.entity.User;
import mine.valley.model.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends BaseService {

	@Autowired
	private ApplyService applyService;

	private Role getRole(Short userType) {
		String roleName = RoleType.getRoleName(userType);
		return (Role) baseDao.find("FROM Role WHERE name = ? AND isDeleted = false", roleName).get(0);
	}

	public void save(User user) {
		user.setRoles(Arrays.asList(new Role[] { getRole(RoleType.USER.getValue()), getRole(user.getType()) }));
		if (user.getId() == null) {
			user.setCreateTime();
		}
		user.setCreateTime();
		baseDao.save(user);
	}

	public void delete(Long id) {
		User user = baseDao.get(User.class, id);
		baseDao.delete(user);
	}

	public User getUserByName(String loginName) {
		List<User> users = baseDao.find("FROM User WHERE loginName = ? AND isDeleted = false", loginName);
		return users.size() == 0 ? new User() : users.get(0);
	}

	public Page<User> getUserList(Page<User> page, Short type) {
		baseDao.findByPage("FROM User WHERE" + (type == null ? "" : " type = " + type + " AND") + " isDeleted = false", new Object[] {}, page);
		for (User user : page.getResult()) {
			user.setApplyCreator(applyService.getApplyList(ApplyType.CREATOR.getValue(), user.getId()));
			user.setApplyAuditor(applyService.getApplyList(ApplyType.AUDITOR.getValue(), user.getId()));
		}
		return page;
	}
}
