package store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import store.bean.Address;
import store.bean.Cart;
import store.bean.Goods;
import store.service.IAddressService;
import store.service.ICartService;
import store.service.IGoodsService;
import store.service.IOrderService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Resource(name = "orderService")
	private IOrderService orderService;

	@Resource(name = "addressService")
	private IAddressService addressService;

	@Resource(name = "cartService")
	private ICartService cartService;

	@Resource(name = "goodsService")
	private IGoodsService goodsService;

	@RequestMapping("/confirm.do")
	public String handleConfirmOrder(String cartId, ModelMap modelMap, HttpSession session) {
		Integer uid = getUidFromSession(session);
		// 获取收货人地址列表
		List<Address> addresslist = addressService.getAddressListByUid(uid);
		String[] ids = cartId.split(",");
		Integer id;
		// 获取购物车列表数据信息
		List<Cart> cartList = new ArrayList<Cart>();
		for (int i = 0; i < ids.length; i++) {
			id = Integer.valueOf(ids[i]);
			Cart cart = cartService.getCartById(uid, id);
			Goods goods = goodsService.getGoodsById(cart.getGoodsId());
			cart.setGoods(goods);
			cartList.add(cart);
		}
		System.out.println(addresslist);
		modelMap.addAttribute("cartList", cartList);
		modelMap.addAttribute("addressList", addresslist);
		return "order_confirm";
	}

}
