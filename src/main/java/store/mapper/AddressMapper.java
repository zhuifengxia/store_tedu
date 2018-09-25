package store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import store.bean.Address;

public interface AddressMapper {

	/**
	 * 新增地址操作
	 * 
	 * @param address
	 * @return
	 */
	Integer insert(Address address);

	/**
	 * 获取某个用户的地址列表数据
	 * 
	 * @param uid
	 * @return
	 */
	List<Address> getAddressListByUid(Integer uid);

	/**
	 * 删除地址操作
	 * 
	 * @param id地址id
	 * @param uid用户id
	 * @return
	 */
	Integer delete(@Param("id") Integer id, @Param("uid") Integer uid);

	/**
	 * 修改地址
	 * 
	 * @param address
	 * @return
	 */
	Integer update(Address address);

	/**
	 * 根据用户id和地址id获取地址详情数据
	 * 
	 * @param id
	 * @param uid
	 * @return
	 */
	Address getAddressByIdAndUid(@Param("id") Integer id, @Param("uid") Integer uid);

	/**
	 * 设置默认地址
	 * 
	 * @param id地址id
	 * @param uid用户id
	 * @return
	 */
	Integer setDefault(@Param("id") Integer id, @Param("uid") Integer uid);

	/**
	 * 取消所有默认地址
	 * 
	 * @param uid用户id
	 * @return
	 */
	Integer cancelAllDefault(@Param("uid") Integer uid);
}
