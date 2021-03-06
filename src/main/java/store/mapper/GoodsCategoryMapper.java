package store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import store.bean.GoodsCategory;

public interface GoodsCategoryMapper {

	/**
	 * 根据父级分类id获取商品分类列表
	 * 
	 * @param parentId父级分类id
	 * @param offset偏移量
	 * @param count获取数据数量
	 * @return
	 */
	List<GoodsCategory> getGoodsCategoryListByParentId(@Param("parentId") Integer parentId,
			@Param("offset") Integer offset, @Param("count") Integer count);
}
