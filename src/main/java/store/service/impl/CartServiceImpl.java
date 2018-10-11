package store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import store.bean.Cart;
import store.ex.DataNotFoundException;
import store.ex.ServiceException;
import store.mapper.CartMapper;
import store.service.ICartService;

@Service("cartService")
public class CartServiceImpl implements ICartService {
	@Resource(name = "cartMapper")
	private CartMapper cartMapper;

	public Integer add(Cart cart) {
		// 查询有没有对应的数据

		Integer count = getRecordCount(cart.getUid(), cart.getGoodsId());
		if (count == 0) {
			// 该用户购物车暂无该商品，则添加新数据
			Integer affectedRows = cartMapper.add(cart);
			if (affectedRows == 1) {
				return cart.getId();
			} else {
				throw new ServiceException("出现未知错误，请联系管理员");
			}
		} else {
			// 购物车中已经有该商品，更改数量即可
			changeGoodsCount(cart.getUid(), cart.getGoodsId(), cart.getGoodsCount());
			return 1;
		}
	}

	public Integer getRecordCount(Integer uid, Integer goodsId) {
		return cartMapper.getRecordCount(uid, goodsId);
	}

	public Integer changeGoodsCount(Integer uid, Integer goodsId, Integer amount) {
		Integer affectedRows = cartMapper.changeGoodsCount(uid, goodsId, amount);
		if (affectedRows != 1) {
			throw new DataNotFoundException();
		}
		return affectedRows;
	}

	public List<Cart> getCartList(Integer uid) {
		return cartMapper.getCartList(uid);
	}

}
