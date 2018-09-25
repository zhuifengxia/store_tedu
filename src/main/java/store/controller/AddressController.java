package store.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import store.bean.Address;
import store.bean.ResponseResult;
import store.ex.DataNotFoundException;
import store.service.IAddressService;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {

	@Resource(name = "addressService")
	private IAddressService addressService;

	/**
	 * 地址管理页面
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/list.do")
	public String showList() {
		return "address_list";

	}

	/**
	 * 获取某个用户的收货地址
	 * 
	 * @return
	 */
	@RequestMapping("/get_list.do")
	@ResponseBody
	public ResponseResult<List<Address>> getAddressByUid(HttpSession session) {
		ResponseResult<List<Address>> rr;
		Integer uid = getUidFromSession(session);
		List<Address> data = addressService.getAddressListByUid(uid);
		rr = new ResponseResult<List<Address>>(1, "查询成功", data);
		return rr;
	}

	/**
	 * 添加地址操作
	 * 
	 * @param address
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleAdd(Address address, HttpSession session) {
		// 获取用户id
		Integer uid = getUidFromSession(session);
		address.setUid(uid);
		Integer test = addressService.add(address);
		ResponseResult<Void> rr = new ResponseResult<Void>(1, "添加成功");
		return rr;
	}

	/**
	 * 删除地址操作
	 * 
	 * @param id地址id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	@ResponseBody
	public ResponseResult<Void> handleDelete(Integer id, HttpSession session) {
		// 获取用户id
		Integer uid = getUidFromSession(session);
		Integer affectedRows = 0;
		ResponseResult<Void> rr;
		try {
			affectedRows = addressService.delete(id, uid);
			if (affectedRows == 1) {
				rr = new ResponseResult<Void>(1, "删除成功");
			} else {
				rr = new ResponseResult<Void>(0, "删除失败");
			}
		} catch (Exception e) {
			rr = new ResponseResult<Void>(0, e);
		}

		return rr;
	}

	/**
	 * 获取地址详情
	 * 
	 * @param id地址id
	 * @param session
	 * @return
	 */
	@RequestMapping("/get.do")
	@ResponseBody
	public ResponseResult<Address> getAddressByIdandUid(Integer id, HttpSession session) {
		// 声明返回值
		ResponseResult<Address> rr;
		// 获取uid--如何获取这些数据
		// id 可以从前端界面获取，uid从session中获取数据
		Integer uid = getUidFromSession(session);
		Address data = addressService.getAddressByIdAndUid(id, uid);
		// 因为前段界面需要拿到数据来显示，因此给状态和数据即可
		rr = new ResponseResult<Address>(1, "查询成功", data);
		// 返回
		return rr;
	}

	/**
	 * 修改地址保存操作
	 * 
	 * @param address地址对象
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/handle_update.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleUpdate(Address address, HttpSession session) {
		// 获取用户id
		Integer uid = getUidFromSession(session);
		address.setUid(uid);
		Integer affectedRows = addressService.update(address);
		ResponseResult<Void> rr;
		if (affectedRows == 1) {
			rr = new ResponseResult<Void>(1, "修改成功");
		} else {
			rr = new ResponseResult<Void>(0, "修改失败");
		}

		return rr;
	}

	/**
	 * 设置默认地址操作
	 * 
	 * @param id地址id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/set_default.do")
	@ResponseBody
	public ResponseResult<Void> handleSetDefault(Integer id, HttpSession session) {
		// 声明返回值
		ResponseResult<Void> rr;
		// 执行
		Integer uid = getUidFromSession(session);
		try {
			addressService.setDefault(id, uid);
			rr = new ResponseResult<Void>(1, "更改默认地址成功");
		} catch (DataNotFoundException e) {
			rr = new ResponseResult<Void>(0, e);
		}
		// 返回
		return rr;
	}

}
