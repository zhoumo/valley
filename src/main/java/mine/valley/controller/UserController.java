package mine.valley.controller;

import mine.valley.base.BaseController;
import mine.valley.constant.ApplyType;
import mine.valley.entity.Message;
import mine.valley.entity.User;
import mine.valley.model.Authority;
import mine.valley.model.Page;
import mine.valley.service.ApplyService;
import mine.valley.service.MessageService;
import mine.valley.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private ApplyService applyService;

	@RequestMapping("/getAuthority.do")
	@ResponseBody
	public Authority getAuthority() {
		Authority authority = new Authority();
		authority.setLoginName(getUser().getLoginName());
		authority.setRealName(getUser().getRealName());
		authority.setApplyCreator(applyService.isApplied(ApplyType.CREATOR.getValue(), getUser().getId()));
		authority.setApplyAuditor(applyService.isApplied(ApplyType.AUDITOR.getValue(), getUser().getId()));
		authority.setApproveCreator(applyService.isApproved(ApplyType.CREATOR.getValue(), getUser().getId()));
		authority.setApproveAuditor(applyService.isApproved(ApplyType.AUDITOR.getValue(), getUser().getId()));
		return authority;
	}

	@RequestMapping("/getMessage.do")
	@ResponseBody
	public Page<Message> getMessage(Page<Message> page) {
		return messageService.getNoReadMessage(page, getUser());
	}

	@RequestMapping("/readAllMessage.do")
	public String readAllMessage() {
		messageService.readAllMessage(getUser());
		return ROOT_PATH;
	}

	@RequestMapping("/registerUser.do")
	public String registerUser(User user) {
		userService.save(user);
		return ROOT_PATH;
	}

	@RequestMapping("/saveUser.do")
	public String saveUser(User user) {
		userService.save(user);
		return ACTIVE_USER;
	}

	@RequestMapping("/updateUser.do")
	public String updateUser(String realName, String password) {
		User user = getUser();
		user.setRealName(realName);
		user.setPassword(password);
		userService.save(user);
		return ROOT_PATH;
	}

	@RequestMapping("/deleteUser.do")
	public String deleteUser(Long id) {
		userService.delete(id);
		return ACTIVE_USER;
	}

	@RequestMapping("/getUserList.do")
	@ResponseBody
	public Page<User> getUserList(Page<User> page, String type) {
		return userService.getUserList(page, (StringUtils.isEmpty(type) || "undefined".equals(type)) ? null : Short.parseShort(type));
	}

	@RequestMapping("/loginNameUnique.do")
	@ResponseBody
	public Boolean loginNameUnique(String loginName) {
		return userService.getUserByName(loginName).getId() == null ? true : false;
	}
}
