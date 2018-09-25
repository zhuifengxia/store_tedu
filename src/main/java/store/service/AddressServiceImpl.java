package store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import store.bean.Address;
import store.ex.DataNotFoundException;
import store.mapper.AddressMapper;

@Service("addressService")
public class AddressServiceImpl implements IAddressService {
	@Resource(name = "addressMapper")
	private AddressMapper addressMapper;

	/**
	 * 添加地址 返回id
	 */
	public Integer add(Address address) {
		List<Address> addresses = getAddressListByUid(address.getUid());
		if (addresses.size() == 0) {
			// 说明这是添加的第一个地址，设置为默认地址
			address.setIsDefault(1);
		} else {
			address.setIsDefault(0);
		}
		// 执行增加
		addressMapper.insert(address);
		// 返回新增id
		return address.getId();
	}

	/**
	 * 获取用户id的地址列表数据
	 */
	public List<Address> getAddressListByUid(Integer uid) {
		return addressMapper.getAddressListByUid(uid);
	}

	/**
	 * 删除地址
	 */
	@Transactional
	public Integer delete(Integer id, Integer uid) {
		// 查询当前要删除的数据信息
		Address address = addressMapper.getAddressByIdAndUid(id, uid);
		if (address == null) {
			// 说明已经删除过了，没数据了
			throw new DataNotFoundException("数据不存在");
		} else {
			Integer affectedRows = addressMapper.delete(id, uid);
			if (address.getIsDefault() == 1) {
				// 说明删除的是默认数据，需要另外设置一个默认值
				List<Address> list = getAddressListByUid(uid);
				if (list.size() > 0) {
					// 取第一条数据
					Integer defaultId = list.get(0).getId();
					System.out.println(defaultId);
					addressMapper.setDefault(defaultId, uid);
				}
			}
			return affectedRows;
		}

	}

	/**
	 * 修改地址信息
	 */
	public Integer update(Address address) {
		return addressMapper.update(address);
	}

	/**
	 * 获取地址详情数据
	 */
	public Address getAddressByIdAndUid(Integer id, Integer uid) {
		return addressMapper.getAddressByIdAndUid(id, uid);
	}

	/**
	 * 设置默认地址
	 */
	@Transactional
	public Integer setDefault(Integer id, Integer uid) {
		// 先取消所有默认地址
		Integer affectedRows = addressMapper.cancelAllDefault(uid);
		// 设置新的默认地址
		affectedRows = addressMapper.setDefault(id, uid);
		if (affectedRows == 0) {
			throw new DataNotFoundException("没有查询到用户的任何收货地址！！");
		} else {
			return affectedRows;
		}
	}

}
