package store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import store.bean.Area;
import store.bean.City;
import store.bean.Province;
import store.mapper.DistrictMapper;
import store.service.IDistrictService;

@Service("districtService")
public class DistrictServiceImpl implements IDistrictService {
	@Resource(name = "districtMapper")
	private DistrictMapper districtMapper;

	public List<Province> getProvinces() {
		return districtMapper.getProvinces();
	}

	public List<City> getCities(String provinceCode) {
		return districtMapper.getCities(provinceCode);
	}

	public List<Area> getAreaes(String cityCode) {
		return districtMapper.getAreaes(cityCode);
	}

}
