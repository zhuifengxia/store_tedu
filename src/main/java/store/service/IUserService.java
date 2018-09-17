package store.service;

import store.bean.User;

public interface IUserService {
	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	Integer register(User user);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	User findUserByUsername(String username);

	/**
	 * 查询邮箱是否被占用
	 * 
	 * @param email
	 * @return
	 */
	boolean checkEmailExists(String email);

	/**
	 * 查询手机号是否被占用
	 * 
	 * @param phone
	 * @return
	 */
	boolean checkPhoneExists(String phone);

	/**
	 * 查看用户名是否存在
	 * 
	 * @param username
	 * @return
	 */
	boolean checkUsernameExists(String username);

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 */
	User login(String username, String password);
}
