package com.lionxxw.babasport.core.service.country.impl;

import com.lionxxw.babasport.core.dao.country.CityDao;
import com.lionxxw.babasport.core.dto.country.City;
import com.lionxxw.babasport.core.query.country.CityQuery;
import com.lionxxw.babasport.core.service.country.CityService;
import com.lionxxw.common.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 市
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Resource
	CityDao cityDao;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addCity(City city) {
		return cityDao.addCity(city);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public City getCityByKey(Integer id) {
		return cityDao.getCityByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<City> getCitysByKeys(List<Integer> idList) {
		return cityDao.getCitysByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return cityDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return cityDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateCityByKey(City city) {
		return cityDao.updateCityByKey(city);
	}
	
	@Transactional(readOnly = true)
	public PageResult<City> getCityListWithPage(CityQuery cityQuery) {
		int total = cityDao.getCityListCount(cityQuery);
		if (total > 0){
			List<City> list = cityDao.getCityListWithPage(cityQuery);
			return new PageResult<City>(total, cityQuery.getPageSize(), list);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<City> getCityList(CityQuery cityQuery) {
		return cityDao.getCityList(cityQuery);
	}
}
