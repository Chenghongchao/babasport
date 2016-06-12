package com.lionxxw.babasport.core.dao.country;

import com.lionxxw.babasport.core.dto.country.City;
import com.lionxxw.babasport.core.query.country.CityQuery;

import java.util.List;


public interface CityDao {

	/**
	 * 添加
	 * @param city
	 */
	Integer addCity(City city);

	/**
	 * 根据主键查找
	 */
	City getCityByKey(Integer id);

	/**
	 * 根据主键批量查找
	 */
	List<City> getCitysByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 */
	Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 */
	Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 */
	Integer updateCityByKey(City city);

	/**
	 * 分页查询
	 * @param cityQuery
	 */
	List<City> getCityListWithPage(CityQuery cityQuery);

	/**
	 * 集合查询
	 * @param cityQuery
	 */
	List<City> getCityList(CityQuery cityQuery);
	
	/**
	 * 总条数
	 * @param cityQuery
	 */
	int getCityListCount(CityQuery cityQuery);
}
