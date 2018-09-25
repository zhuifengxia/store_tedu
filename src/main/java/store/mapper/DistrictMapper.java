package store.mapper;

import java.util.List;

import store.bean.Area;
import store.bean.City;
import store.bean.Province;

public interface DistrictMapper {

	/**
	 * 获取所有省的列表数据
	 * 
	 * @return
	 */
	List<Province> getProvinces();

	/**
	 * 获取某个省的城市列表数据
	 * 
	 * @param provinceCode
	 * @return
	 */
	List<City> getCities(String provinceCode);

	/**
	 * 获取某个城市的区列表数据
	 * 
	 * @param cityCode
	 * @return
	 */
	List<Area> getAreaes(String cityCode);
}
