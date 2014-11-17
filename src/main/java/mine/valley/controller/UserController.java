package mine.valley.controller;

import javax.servlet.http.HttpSession;

import mine.valley.base.BaseController;
import mine.valley.entity.User;
import mine.valley.model.Authority;
import mine.valley.model.Page;
import mine.valley.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index(HttpSession session) {
		User user = (User) session.getAttribute(super.getUserName());
		if (user.getUserType().equals(User.USER_TYPE_ADMIN)) {
			return "admin";
		} else {
			return "home";
		}
	}

	@RequestMapping("/getAuthority.do")
	@ResponseBody
	public Authority getAuthority(HttpSession session) {
		User user = (User) session.getAttribute(super.getUserName());
		Authority authority = new Authority(user.getUserName(), user.getRealName());
		return authority;
	}

	@RequestMapping("/registerUser.do")
	public String registerUser(User user) {
		userService.saveOrUpdate(user);
		return "redirect:/";
	}

	@RequestMapping("/saveUser.do")
	public String saveUser(User user) {
		userService.saveOrUpdate(user);
		return "redirect:/?user";
	}

	@RequestMapping("/deleteUser.do")
	public String deleteUser(Long id) {
		userService.delete(id);
		return "redirect:/?user";
	}

	@RequestMapping("/getUserList.do")
	@ResponseBody
	public Page<User> getUserList(Page<User> page) {
		return userService.getUserList(page);
	}
}
