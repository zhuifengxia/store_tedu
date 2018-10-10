package store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import store.bean.Goods;

public interface GoodsMapper {

	/**
	 * 根据商品分类获取商品列表
	 * 
	 * @param categoryId分类id
	 * @param orderBy排序方式
	 * @param offset偏移量
	 * @param count每页个数
	 * @return
	 */
	List<Goods> getGoodsListByCategoryId(@Param("categoryId") Integer categoryId, @Param("orderBy") String orderBy,
			@Param("offset") Integer offset, @Param("count") Integer count);

	/**
	 * 根据分类id获取商品总数
	 * 
	 * @param categoryId分类id
	 * @return
	 */
	Integer getGoodsCountByCategoryId(Integer categoryId);

	/**
	 * 根据商品id获取商品信息
	 * 
	 * @param id商品id
	 * @return
	 */
	Goods getGoodsById(Integer id);

	/**
	 * 根据item_type获取商品列表数据
	 * 
	 * @param itemType
	 * @return
	 */
	List<Goods> getGoodsListByItemType(String itemType);
}
