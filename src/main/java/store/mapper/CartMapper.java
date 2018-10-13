package store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import store.bean.Cart;

public interface CartMapper {

	/**
	 * 添加购物车
	 * 
	 * @param cart
	 * @return受影响的行数
	 */
	Integer add(Cart cart);

	/**
	 * 根据用户id和商品id查询当前用户是否加入购物车
	 * 
	 * @param uid用户id
	 * @param goodsId商品id
	 * @return
	 */
	Integer getRecordCount(@Param("uid") Integer uid, @Param("goodsId") Integer goodsId);

	/**
	 * 调整购物车数量
	 * 
	 * @param uid
	 * @param goodsId
	 * @param amount
	 * @return
	 */
	Integer changeGoodsCount(@Param("uid") Integer uid, @Param("goodsId") Integer goodsId,
			@Param("amount") Integer amount);

	/**
	 * 获取用户的购物车列表
	 * 
	 * @param uid用户id
	 * @return
	 */
	List<Cart> getCartList(@Param("uid") Integer uid);

	/**
	 * 根据购物车id获取购物车数据信息
	 * 
	 * @return
	 */
	Cart getCartById(@Param("uid") Integer uid, @Param("id") Integer id);
}
