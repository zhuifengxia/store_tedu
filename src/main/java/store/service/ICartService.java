package store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import store.bean.Cart;

public interface ICartService {

	/**
	 * 添加购物车
	 * 
	 * @param cart
	 * @return
	 */
	Integer add(Cart cart);

	/**
	 * 根据用户id和商品id查询当前用户是否加入购物车
	 * 
	 * @param uid用户id
	 * @param goodsId商品id
	 * @return
	 */
	Integer getRecordCount(Integer uid, Integer goodsId);

	/**
	 * 调整购物车数量
	 * 
	 * @param uid用户id
	 * @param goodsId商品id
	 * @param amount购买数量
	 * @return
	 */
	Integer changeGoodsCount(Integer uid, Integer goodsId, Integer amount);

	/**
	 * 获取用户的购物车列表
	 * 
	 * @param uid用户id
	 * @return
	 */
	List<Cart> getCartList(@Param("uid") Integer uid);
}
