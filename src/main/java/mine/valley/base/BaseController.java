package mine.valley.base;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import mine.valley.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseController {

	@Autowired
	protected HttpSession session;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected static final String ROOT_PATH = "redirect:/";

	protected static final String ACTIVE_JOB = "redirect:/#?active=job";

	protected static final String ACTIVE_USER = "redirect:/#?active=user";

	protected static final String ACTIVE_PAPER = "redirect:/#?active=paper";

	protected static final String PAPER_LIST_FOR_CREATE = "redirect:/#/paper/list?type=create";

	protected static final String PAPER_LIST_FOR_AUDITED = "redirect:/#/paper/list?type=audited";

	protected static final String PAPER_RENEW = "redirect:/#/paper/create?id=";

	protected String getUserName() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}

	protected User getUser() {
		return (User) session.getAttribute(getUserName());
	}

	protected String transcode(String text) {
		try {
			return new String(text.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("transcode failed.", e);
			return text;
		}
	}
}
