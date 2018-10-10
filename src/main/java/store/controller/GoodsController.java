package store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import store.bean.Goods;
import store.service.IGoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

	@Resource(name = "goodsService")
	private IGoodsService goodsService;

	/**
	 * 商品列表页面
	 * 
	 * @param categoryId分类id
	 * @param page请求页码
	 * @param orderBy排序方式
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/list.do")
	public String showGoodsListByCategoryId(Integer categoryId, Integer page, Integer orderBy, ModelMap modelMap) {
		List<Goods> goodsList;
		Integer goodsCount;
		if (categoryId == null || categoryId < 1) {
			modelMap.addAttribute("msg", "请求参数错误");
			return "error";// 跳转到错误页面
		}
		// 获取商品总数
		goodsCount = goodsService.getGoodsCountByCategoryId(categoryId);
		// 设置每页显示数量
		goodsService.setCountPerPage(IGoodsService.COUNT_PER_PAGE);
		// 获取每页显示的数量
		Integer countPerPage = goodsService.getCountPerPage();
		// 商品的总页数
		Integer pages = goodsCount / countPerPage;
		pages += goodsCount % countPerPage == 0 ? 0 : 1;
		if (page == null || page < 1) {
			page = 1;
		}
		String orderByStr;
		if (orderBy == null || orderBy < 0 || orderBy >= IGoodsService.ORDER_BY.length) {
			orderBy = 0;
		}
		// 获取排序方式
		orderByStr = IGoodsService.ORDER_BY[orderBy];

		// 获取商品列表数据
		goodsList = goodsService.getGoodsListByCategoryId(categoryId, orderByStr, page);
		modelMap.addAttribute("goodsList", goodsList);
		modelMap.addAttribute("goodsCount", goodsCount);
		modelMap.addAttribute("pages", pages);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("countPerPage", countPerPage);
		return "goods_list";
	}

	/**
	 * 商品详情页面
	 * 
	 * @param id商品id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/details.do")
	public String showGoodsDetails(@RequestParam(value = "id", required = true) Integer id, ModelMap modelMap) {
		Goods goods = goodsService.getGoodsById(id);
		if (goods != null) {
			// 获取相同商品但规格不同的列表数据
			List<Goods> goodsList = goodsService.getGoodsListByItemType(goods.getItemType());
			modelMap.addAttribute("goods", goods);
			modelMap.addAttribute("goodsList", goodsList);
			return "goods_details";
		} else {
			// 没有获取到数据
			return "error";
		}
	}
}
