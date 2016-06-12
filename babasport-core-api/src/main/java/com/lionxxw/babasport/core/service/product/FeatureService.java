package com.lionxxw.babasport.core.service.product;

import com.lionxxw.babasport.core.dto.product.Feature;
import com.lionxxw.babasport.core.query.product.FeatureQuery;
import com.lionxxw.common.model.PageResult;

import java.util.List;


/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午01:50:28]
 */
public interface FeatureService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addFeature(Feature feature);

	/**
	 * 根据主键查询
	 */
	Feature getFeatureByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	List<Feature> getFeaturesByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * 
	 * @return
	 */
	Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	Integer updateFeatureByKey(Feature feature);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param featureQuery
	 *            查询条件
	 * @return
	 */
	PageResult<Feature> getFeatureListWithPage(FeatureQuery featureQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param featureQuery
	 *            查询条件
	 * @return
	 */
	List<Feature> getFeatureList(FeatureQuery featureQuery);
}
