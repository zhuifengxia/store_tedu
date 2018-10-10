package store.service;

import java.util.List;

import store.bean.Goods;

public interface IGoodsService {
	// 商品列表默认排序方式
	String ORDER_BY_DEFAULT = "priority DESC";
	String ORDER_BY_PRICE_ASC = "price ASC";
	String ORDER_BY_PRICE_DESC = "price DESC";
	// 排序方案集合
	String[] ORDER_BY = { ORDER_BY_DEFAULT, ORDER_BY_PRICE_ASC, ORDER_BY_PRICE_DESC };

	// 每页显示多少条数据
	Integer COUNT_PER_PAGE = 20;

	// 设置每页显示数量
	void setCountPerPage(Integer countPerPage);

	// 获取每页显示数量
	Integer getCountPerPage();

	/**
	 * 根据商品分类获取商品列表
	 * 
	 * @param categoryId分类id
	 * @param orderBy排序方式
	 * @param offset偏移量
	 * @param count每页个数
	 * @return
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy, Integer offset, Integer count);

	/**
	 * 根据商品分类获取商品列表，默认结果列表根据priority排序
	 * 
	 * @param categoryId分类id
	 * @param offset偏移量
	 * @param count每页个数
	 * @return
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, Integer offset, Integer count);

	/**
	 * 根据商品分类获取商品列表
	 * 
	 * @param categoryId分类id
	 * @param orderBy排序方式
	 * @param page请求页码
	 * @return
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy, Integer page);

	/**
	 * 根据商品分类获取商品列表，默认结果列表根据priority排序
	 * 
	 * @param categoryId分类id
	 * @param page请求页码
	 * @return
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, Integer page);

	/**
	 * 根据商品分类获取商品列表（第一页）
	 * 
	 * @param categoryId分类id
	 * @param orderBy排序方式
	 * @return
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy);

	/**
	 * 根据商品分类获取商品列表（第一页数据）默认结果列表根据priority排序
	 * 
	 * @param categoryId分类id
	 * @return
	 */
	List<Goods> getGoodsListByCategoryId(Integer categoryId);

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
