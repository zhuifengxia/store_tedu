package store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import store.bean.Goods;
import store.mapper.GoodsMapper;
import store.service.IGoodsService;

@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService {

	@Resource(name = "goodsMapper")
	private GoodsMapper goodsMapper;

	private Integer countPerPage = COUNT_PER_PAGE;

	public void setCountPerPage(Integer countPerPage) {
		if (countPerPage >= 5 && countPerPage <= 30) {
			this.countPerPage = countPerPage;
		}
	}

	public Integer getCountPerPage() {
		return this.countPerPage;
	}

	/**
	 * 根据商品分类获取商品列表
	 * 
	 * @param categoryId分类id
	 * @param orderBy排序方式
	 * @param offset偏移量
	 * @param count每页个数
	 * @return
	 */
	public List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy, Integer offset, Integer count) {
		return goodsMapper.getGoodsListByCategoryId(categoryId, orderBy, offset, count);
	}

	public List<Goods> getGoodsListByCategoryId(Integer categoryId, Integer offset, Integer count) {
		return getGoodsListByCategoryId(categoryId, ORDER_BY_DEFAULT, offset, count);
	}

	public List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy, Integer page) {
		Integer offset = (page - 1) * getCountPerPage();
		return getGoodsListByCategoryId(categoryId, orderBy, offset, getCountPerPage());
	}

	public List<Goods> getGoodsListByCategoryId(Integer categoryId, Integer page) {
		return getGoodsListByCategoryId(categoryId, ORDER_BY_DEFAULT, page);
	}

	public List<Goods> getGoodsListByCategoryId(Integer categoryId, String orderBy) {
		return getGoodsListByCategoryId(categoryId, orderBy, 1);
	}

	public List<Goods> getGoodsListByCategoryId(Integer categoryId) {
		getGoodsListByCategoryId(categoryId, 1);
		return null;
	}

	public Integer getGoodsCountByCategoryId(Integer categoryId) {
		return goodsMapper.getGoodsCountByCategoryId(categoryId);
	}

	public Goods getGoodsById(Integer id) {
		return goodsMapper.getGoodsById(id);
	}

	public List<Goods> getGoodsListByItemType(String itemType) {
		return goodsMapper.getGoodsListByItemType(itemType);
	}

}
