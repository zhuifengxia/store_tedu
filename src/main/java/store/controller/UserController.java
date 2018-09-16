package store.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import store.bean.ResponseResult;
import store.service.IUserService;

@RequestMapping("/user")
@Controller
public class UserController {

	@Resource(name = "userService")
	private IUserService userService;

	/**
	 * 注册页面
	 * 
	 * @return
	 */
	@RequestMapping("/register.do")
	public String showRegister() {
		return "register";
	}

	/**
	 * 查看用户名是否被占用
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping("/check_username.do")
	@ResponseBody
	public ResponseResult<Void> checkUsername(String username) {
		ResponseResult<Void> rr;
		boolean result = userService.checkUsernameExists(username);
		if (result) {
			// 用户名已经存在
			rr = new ResponseResult<Void>(0, "用户名已经被占用");
		} else {
			// 可以注册
			rr = new ResponseResult<Void>(1, "用户名可以使用");
		}
		return rr;
	}
}
