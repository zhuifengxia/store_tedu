package store.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import store.bean.Area;
import store.bean.City;
import store.bean.Province;
import store.bean.ResponseResult;
import store.service.IDistrictService;

@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

	@Resource(name = "districtService")
	private IDistrictService districtService;

	/**
	 * 获取所有省的数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/provinces.do")
	@ResponseBody
	public ResponseResult<List<Province>> getProvinces() {
		// 获取所有省的数据信息
		List<Province> provinces = districtService.getProvinces();
		ResponseResult<List<Province>> rr = new ResponseResult<List<Province>>(1, "获取省列表成功", provinces);
		return rr;
	}

	/**
	 * 获取某个省的城市列表数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cities.do")
	@ResponseBody
	public ResponseResult<List<City>> getCities(String provinceCode) {
		List<City> cities = districtService.getCities(provinceCode);
		ResponseResult<List<City>> rr = new ResponseResult<List<City>>(1, "获取市列表成功", cities);
		return rr;
	}

	/**
	 * 获取某个城市的区列表数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/areas.do")
	@ResponseBody
	public ResponseResult<List<Area>> getAreaes(String cityCode) {
		List<Area> areas = districtService.getAreaes(cityCode);
		ResponseResult<List<Area>> rr = new ResponseResult<List<Area>>(1, "获取区列表成功", areas);
		return rr;
	}

}
