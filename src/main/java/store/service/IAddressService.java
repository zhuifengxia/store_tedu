package store.service;

import java.util.List;

import store.bean.Address;

public interface IAddressService {

	/**
	 * 添加新增地址
	 * 
	 * @param address地址信息
	 * @return 新地址id
	 */
	Integer add(Address address);

	/**
	 * 删除地址
	 * 
	 * @param id
	 *            地址id
	 * @param uid
	 *            地址所属用户id
	 * @return
	 */
	Integer delete(Integer id, Integer uid);

	/**
	 * 获取某个用户的地址列表数据
	 * 
	 * @param uid
	 * @return
	 */
	List<Address> getAddressListByUid(Integer uid);

	/**
	 * 修改地址信息
	 * 
	 * @param address
	 * @return
	 */
	Integer update(Address address);

	/**
	 * 根据地址id和用户id获取地址详情数据
	 * 
	 * @param id
	 * @param uid
	 * @return
	 */
	Address getAddressByIdAndUid(Integer id, Integer uid);

	/**
	 * 设置默认地址
	 * 
	 * @param id地址id
	 * @param uid用户id
	 * @return
	 */
	Integer setDefault(Integer id, Integer uid);
}
