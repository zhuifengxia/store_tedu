package store.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import store.bean.Cart;
import store.bean.Goods;
import store.bean.ResponseResult;
import store.service.ICartService;
import store.service.IGoodsService;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

	@Resource(name = "cartService")
	private ICartService cartService;

	@Resource(name = "goodsService")
	private IGoodsService goodsService;

	/**
	 * 添加购物车操作
	 * 
	 * @param goodsId商品id
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public ResponseResult<Void> handleAddToCart(Integer goodsId, Integer goodsCount, HttpSession session) {
		Integer uid = getUidFromSession(session);
		Cart cart = new Cart();
		cart.setUid(uid);
		cart.setisCollect(0);
		cart.setGoodsCount(goodsCount);
		cart.setGoodsId(goodsId);
		cart.setCreatedTime(new Date());
		ResponseResult<Void> rr;
		try {
			cartService.add(cart);
			rr = new ResponseResult<Void>(1, "添加购物车成功");
		} catch (Exception e) {
			rr = new ResponseResult<Void>(0, e);
		}
		return rr;
	}

	/**
	 * 购物车列表
	 * 
	 * @param modelMap
	 * @param session
	 * @return
	 */
	@RequestMapping("/list.do")
	public String showList(ModelMap modelMap, HttpSession session) {
		Integer uid = getUidFromSession(session);
		List<Cart> carts = cartService.getCartList(uid);
		for (Cart cart : carts) {
			Goods goods = goodsService.getGoodsById(cart.getGoodsId());
			cart.setGoods(goods);
		}
		System.out.println(carts);
		modelMap.addAttribute("carts", carts);
		return "cart_list";
	}
}
