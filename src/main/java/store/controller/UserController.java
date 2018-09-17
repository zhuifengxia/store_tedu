package store.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import store.bean.ResponseResult;
import store.bean.User;
import store.ex.PasswordNotMatchException;
import store.ex.UsernameAlreadyExistsException;
import store.ex.UsernameNotFoundException;
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
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/login.do")
	public String showLogin() {
		return "login";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String handle_Logout(HttpServletRequest request, HttpSession session) {
		// 清除session
		session.invalidate();
		// 重定向到主页
		return "redirect:../main/index.do";
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

	/**
	 * 检测邮箱是否被占用
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("/check_email.do")
	@ResponseBody
	public ResponseResult<Void> checkEmail(String email) {
		ResponseResult<Void> rr;
		boolean result = userService.checkEmailExists(email);
		if (result) {
			// 邮箱已经存在
			rr = new ResponseResult<Void>(0, "邮箱已经被占用");
		} else {
			// 邮箱可以使用
			rr = new ResponseResult<Void>(1, "邮箱可以使用");
		}
		return rr;
	}

	/**
	 * 检测手机号是否被占用
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("/check_phone.do")
	@ResponseBody
	public ResponseResult<Void> checkPhone(String phone) {
		ResponseResult<Void> rr;
		boolean result = userService.checkPhoneExists(phone);
		if (result) {
			// 手机号已经存在
			rr = new ResponseResult<Void>(0, "手机号已经被占用");
		} else {
			// 手机号可以使用
			rr = new ResponseResult<Void>(1, "手机号可以使用");
		}
		return rr;
	}

	/**
	 * 注册操作
	 * 
	 * @param username
	 * @param password
	 * @param phone
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/handle_register.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleRegister(@RequestParam("uname") String username,
			@RequestParam("upwd") String password, @RequestParam("phone") String phone,
			@RequestParam("email") String email) {
		ResponseResult<Void> rr;
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		try {
			userService.register(user);
			rr = new ResponseResult<Void>(1, "注册成功");
		} catch (UsernameAlreadyExistsException e) {
			rr = new ResponseResult<Void>(0, e.getMessage());
		}
		return rr;
	}

	/**
	 * 登录操作
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/handle_login.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleLogin(String username, String password, HttpSession session) {
		ResponseResult<Void> rr;
		try {
			User user = userService.login(username, password);
			session.setAttribute("user", user);
			// 登录成功
			rr = new ResponseResult<Void>(1);
		} catch (PasswordNotMatchException e) {
			// 密码错误
			rr = new ResponseResult<Void>(-1, e.getMessage());
		} catch (UsernameNotFoundException e) {
			// 用户名不存在
			rr = new ResponseResult<Void>(0, e.getMessage());
		}

		return rr;
	}
}
