package store.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import store.bean.ResponseResult;
import store.bean.User;
import store.ex.PasswordNotMatchException;
import store.ex.UserNotFoundException;
import store.ex.UsernameAlreadyExistsException;
import store.ex.UsernameNotFoundException;
import store.service.IUserService;

@RequestMapping("/user")
@Controller
public class UserController extends BaseController {

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
	 * 修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping("/password.do")
	public String showPassword() {
		return "user_password";
	}

	/**
	 * 修改个人信息页面
	 * 
	 * @return
	 */
	@RequestMapping("/profile.do")
	public String showProfile(ModelMap modelMap, HttpSession session) {
		// 获取当前用户的个人数据信息
		Integer uid = getUidFromSession(session);
		User user = userService.findUserById(uid);
		// 将数据转发到前端页面
		modelMap.addAttribute("user", user);
		return "user_profile";
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
	 * @param username用户名
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
	 * @param email邮箱
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
	 * @param email邮箱
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
	 * @param username用户名
	 * @param password密码
	 * @param phone手机号
	 * @param email邮箱
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
			rr = new ResponseResult<Void>(0, e);
		}
		return rr;
	}

	/**
	 * 登录操作
	 * 
	 * @param username登录名
	 * @param password登录密码
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
			rr = new ResponseResult<Void>(-1, e);
		} catch (UsernameNotFoundException e) {
			// 用户名不存在
			rr = new ResponseResult<Void>(0, e);
		}

		return rr;
	}

	/**
	 * 修改密码操作
	 * 
	 * @param oldPassword旧密码
	 * @param newPassword新密码
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/handle_change_password.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleChangePassword(String oldPassword, String newPassword, HttpSession session) {
		ResponseResult<Void> rr;
		try {
			Integer uid = getUidFromSession(session);
			userService.changePassword(uid, oldPassword, newPassword);
			rr = new ResponseResult<Void>(1, "修改成功");
		} catch (UserNotFoundException e) {
			rr = new ResponseResult<Void>(-1, e);
		} catch (PasswordNotMatchException e) {
			rr = new ResponseResult<Void>(-2, e);
		}
		return rr;
	}

	/**
	 * 修改个人信息操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "/handle_edit_profile.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleEditProfile(String username, Integer gender, String phone, String email,
			HttpSession session) {
		ResponseResult<Void> rr;
		try {
			Integer uid = getUidFromSession(session);
			userService.editProfile(uid, username, gender, phone, email);
			User user = userService.findUserById(uid);
			// 更改session
			session.setAttribute("user", user);
			rr = new ResponseResult<Void>(1, "修改成功");
		} catch (UserNotFoundException e) {
			rr = new ResponseResult<Void>(-1, e);
		} catch (UsernameAlreadyExistsException e) {
			rr = new ResponseResult<Void>(-2, e);
		}
		return rr;
	}
}
