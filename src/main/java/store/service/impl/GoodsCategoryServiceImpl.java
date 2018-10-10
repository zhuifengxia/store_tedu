package store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import store.bean.GoodsCategory;
import store.mapper.GoodsCategoryMapper;
import store.service.IGoodsCategoryService;

@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {

	@Resource(name = "goodsCategoryMapper")
	private GoodsCategoryMapper goodsCategoryMapper;

	/**
	 * 根据父级分类id获取商品分类列表
	 * 
	 * @param parentId父级分类id
	 * @param offset偏移量
	 * @param count获取数据数量
	 * @return
	 */
	public List<GoodsCategory> getGoodsCategoryListByParentId(Integer parentId, Integer offset, Integer count) {
		return goodsCategoryMapper.getGoodsCategoryListByParentId(parentId, offset, count);
	}

}
