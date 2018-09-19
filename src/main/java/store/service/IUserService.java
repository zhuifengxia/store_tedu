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
	 * 根据用户id查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	User findUserById(Integer uid);

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

	/**
	 * 修改密码操作
	 * 
	 * @param uid
	 *            用户id
	 * @param oldPassword
	 *            旧密码
	 * @param newPassword
	 *            新密码
	 * @return
	 */
	Integer changePassword(Integer uid, String oldPassword, String newPassword);

	/**
	 * 修改个人信息操作
	 * 
	 * @param uid用户id
	 * @param username新用户名
	 * @param gender新性别
	 * @param phone新电话
	 * @param email新邮箱
	 * @return
	 */
	Integer editProfile(Integer uid, String username, Integer gender, String phone, String email);
}
