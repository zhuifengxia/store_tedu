package store.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import store.bean.User;
import store.ex.UsernameAlreadyExistsException;
import store.mapper.UserMapper;

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
}
