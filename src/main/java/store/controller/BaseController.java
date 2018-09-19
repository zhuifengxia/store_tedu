package store.controller;

import javax.servlet.http.HttpSession;

import store.bean.User;

public abstract class BaseController {

	/**
	 * 从session中获取用户id
	 * 
	 * @return
	 */
	protected Integer getUidFromSession(HttpSession session) {
		Object object = session.getAttribute("user");
		if (object == null) {
			return null;
		} else {
			User user = (User) object;
			return user.getId();
		}
	}
}
