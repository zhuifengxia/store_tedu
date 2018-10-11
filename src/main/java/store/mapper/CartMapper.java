package store.mapper;

import store.bean.Cart;

public interface CartMapper {

	/**
	 * 添加购物车
	 * 
	 * @param cart
	 * @return受影响的行数
	 */
	Integer add(Cart cart);
}
