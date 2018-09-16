package store.mapper;

import store.bean.User;

public interface UserMapper {
	/**
	 * 新增用户数据
	 * 
	 * @param user
	 */
	void insert(User user);

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param username用户名
	 * @return返回匹配的用户数据，没有返回null
	 */
	User findUserByUsername(String username);

	/**
	 * 获取目标邮箱对应的数量集合
	 * 
	 * @param email
	 * @return
	 */
	Integer getRecordCountByEmail(String email);

	/**
	 * 获取目标手机号对应的数量
	 * 
	 * @param phone
	 * @return
	 */
	Integer getRecordCountByPhone(String phone);

}
