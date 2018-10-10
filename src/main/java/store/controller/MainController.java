package store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import store.bean.Goods;
import store.bean.GoodsCategory;
import store.service.IGoodsCategoryService;
import store.service.IGoodsService;

@RequestMapping("/main")
@Controller
public class MainController extends BaseController {

	@Resource(name = "goodsCategoryService")
	private IGoodsCategoryService goodsCategoryService;

	@Resource(name = "goodsService")
	private IGoodsService goodsService;

	@RequestMapping("/index.do")
	public String showIndex(ModelMap modelMap) {
		List<List<GoodsCategory>> goodsCategories = new ArrayList<List<GoodsCategory>>();
		// 获取161的3个二级分类数据
		List<GoodsCategory> categories161 = goodsCategoryService.getGoodsCategoryListByParentId(161, 0, 3);

		// 获取各二级分类对应的三级分类
		List<GoodsCategory> subCategories;
		for (GoodsCategory goodsCategory : categories161) {
			subCategories = goodsCategoryService.getGoodsCategoryListByParentId(goodsCategory.getId(), null, null);
			goodsCategories.add(subCategories);
		}
		// 获取电脑分类中排名前三的商品列表
		List<Goods> computers = goodsService.getGoodsListByCategoryId(163, 0, 3);
		modelMap.addAttribute("categories161", categories161);
		modelMap.addAttribute("goodsCategories", goodsCategories);
		modelMap.addAttribute("computers", computers);
		return "index";
	}

}
