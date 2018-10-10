package store.service.impl;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import store.bean.User;
import store.ex.PasswordNotMatchException;
import store.ex.UserNotFoundException;
import store.ex.UsernameAlreadyExistsException;
import store.ex.UsernameNotFoundException;
import store.mapper.UserMapper;
import store.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource(name = "userMapper")
	private UserMapper userMapper;

	/**
	 * 注册用户
	 */
	public Integer register(User user) {
		// 根据用户名是否被占用
		if (!checkUsernameExists(user.getUsername())) {
			// 新增，执行注册
			// 获取密码进行密码加密
			String pwd = user.getPassword();
			String salt = "我的盐秘钥";
			// 加密后的密码
			String pwdmd5 = DigestUtils.md5Hex(pwd + salt);
			// 加密后的密码存入user
			user.setPassword(pwdmd5);
			userMapper.insert(user);
			return user.getId();
		} else {
			throw new UsernameAlreadyExistsException("用户名已经被占用");
		}
	}

	/**
	 * 根据用户名查询用户数据
	 */
	public User findUserByUsername(String username) {
		User user = userMapper.findUserByUsername(username);
		return user;
	}

	/**
	 * 根据用户id查询用户数据
	 */
	public User findUserById(Integer uid) {
		return userMapper.findUserById(uid);
	}

	/**
	 * 查询邮箱是否被占用
	 */
	public boolean checkEmailExists(String email) {
		return userMapper.getRecordCountByEmail(email) > 0;
	}

	/**
	 * 查看手机号是否被占用
	 */
	public boolean checkPhoneExists(String phone) {
		return userMapper.getRecordCountByPhone(phone) > 0;
	}

	/**
	 * 查询用户名是否被占用
	 */
	public boolean checkUsernameExists(String username) {
		return findUserByUsername(username) != null;
	}

	/**
	 * 用户登录
	 */
	public User login(String username, String password) {
		User user = findUserByUsername(username);
		if (user == null) {
			// 用户名不对
			throw new UsernameNotFoundException("用户名错误");
		} else {
			String salt = "我的盐秘钥";
			String pwdmd5 = DigestUtils.md5Hex(password + salt);
			if (!pwdmd5.equals(user.getPassword())) {
				// 密码错误
				throw new PasswordNotMatchException("密码错误");
			} else {
				// 登录成功
				return user;
			}
		}
	}

	/**
	 * 修改密码操作
	 */
	public Integer changePassword(Integer uid, String oldPassword, String newPassword) {
		User user = findUserById(uid);
		if (user == null) {
			// 该用户不存在
			throw new UserNotFoundException("用户不存在");
		} else {
			String salt = "我的盐秘钥";
			String oldpwdmd5 = DigestUtils.md5Hex(oldPassword + salt);
			if (user.getPassword().equals(oldpwdmd5)) {
				// 修改密码
				User u = new User();
				String newpwdmd5 = DigestUtils.md5Hex(newPassword + salt);
				u.setPassword(newpwdmd5);
				u.setId(uid);
				return userMapper.update(u);
			} else {
				// 旧密码错误不允许修改密码
				throw new PasswordNotMatchException("旧密码不匹配");
			}
		}
	}

	/**
	 * 修改个人信息操作
	 */
	public Integer editProfile(Integer uid, String username, Integer gender, String phone, String email) {
		User user = findUserById(uid);
		if (user == null) {
			// 该用户不存在
			throw new UserNotFoundException("用户不存在");
		} else {
			User u = new User();
			if (!user.getUsername().equals(username)) {
				User user2 = findUserByUsername(username);
				if (user2 != null) {
					if (!user2.getId().equals(uid)) {
						// 根据用户名查询到的用户数据的id和当前id比较不相同说明新修改的用户名已经被占用，否则可以修改
						throw new UsernameAlreadyExistsException("用户名已经被占用");
					}
				} else {
					// 可以更改
					u.setUsername(username);
				}
			}
			u.setId(uid);
			u.setGender(gender);
			if (phone != null && phone.length() >= 11) {
				u.setPhone(phone);
			}
			u.setEmail(email);
			return userMapper.update(u);
		}
	}

}
