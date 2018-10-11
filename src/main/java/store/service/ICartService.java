package store.service;

import store.bean.Cart;

public interface ICartService {

	/**
	 * 添加购物车
	 * 
	 * @param cart
	 * @return
	 */
	Integer add(Cart cart);
}
