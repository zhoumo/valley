package mine.valley.service;

import java.util.Arrays;
import java.util.List;

import mine.valley.base.BaseService;
import mine.valley.entity.Role;
import mine.valley.entity.User;
import mine.valley.model.Page;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends BaseService {

	public void saveOrUpdate(User user) {
		user.setRoles(Arrays.asList(new Role[] { getRole(Role.ROLE_USER), getRole(User.getRoleName(user.getUserType())) }));
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

	public Role getRole(String roleName) {
		return (Role) baseDao.find("FROM Role WHERE roleName = ? AND isDeleted = false", roleName).get(0);
	}

	public User getUserByName(String userName) {
		List<User> users = baseDao.find("FROM User WHERE userName = ? AND isDeleted = false", userName);
		return users.size() == 0 ? new User() : users.get(0);
	}

	public Page<User> getUserList(Page<User> page) {
		return baseDao.findByPage("FROM User WHERE isDeleted = false", new Object[] {}, page);
	}
}
