package store.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import store.bean.Cart;
import store.mapper.CartMapper;
import store.service.ICartService;

@Service("cartService")
public class CartServiceImpl implements ICartService {
	@Resource(name = "cartMapper")
	private CartMapper cartMapper;

	public Integer add(Cart cart) {
		cartMapper.add(cart);
		return cart.getId();
	}

}
