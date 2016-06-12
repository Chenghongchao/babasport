package com.lionxxw.babasport.core.service.product.impl;

import java.util.List;

import javax.annotation.Resource;

import com.lionxxw.babasport.core.dao.product.FeatureDao;
import com.lionxxw.babasport.core.dto.product.Feature;
import com.lionxxw.babasport.core.query.product.FeatureQuery;
import com.lionxxw.babasport.core.service.product.FeatureService;
import com.lionxxw.common.model.PageResult;
import javafx.scene.control.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品属性事务层
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class FeatureServiceImpl implements FeatureService {

	@Resource
	FeatureDao featureDao;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addFeature(Feature feature) {
		return featureDao.addFeature(feature);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Feature getFeatureByKey(Integer id) {
		return featureDao.getFeatureByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Feature> getFeaturesByKeys(List<Integer> idList) {
		return featureDao.getFeaturesByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return featureDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return featureDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateFeatureByKey(Feature feature) {
		return featureDao.updateFeatureByKey(feature);
	}
	
	@Transactional(readOnly = true)
	public PageResult<Feature> getFeatureListWithPage(FeatureQuery featureQuery) {
		int total = featureDao.getFeatureListCount(featureQuery);
		if (total > 0){
			List<Feature> list = featureDao.getFeatureListWithPage(featureQuery);
			return new PageResult<Feature>(total, featureQuery.getPageSize(), list);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Feature> getFeatureList(FeatureQuery featureQuery) {
		return featureDao.getFeatureList(featureQuery);
	}
}
