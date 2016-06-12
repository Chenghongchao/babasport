package com.lionxxw.babasport.core.service.product.impl;

import java.util.List;

import javax.annotation.Resource;

import com.lionxxw.babasport.core.dao.product.TypeDao;
import com.lionxxw.babasport.core.dto.product.Type;
import com.lionxxw.babasport.core.query.product.TypeQuery;
import com.lionxxw.babasport.core.service.product.TypeService;
import com.lionxxw.common.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品类型
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class TypeServiceImpl implements TypeService {

	@Resource
	TypeDao typeDao;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addType(Type type) {
		return typeDao.addType(type);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Type getTypeByKey(Integer id) {
		return typeDao.getTypeByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Type> getTypesByKeys(List<Integer> idList) {
		return typeDao.getTypesByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return typeDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return typeDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateTypeByKey(Type type) {
		return typeDao.updateTypeByKey(type);
	}
	
	@Transactional(readOnly = true)
	public PageResult<Type> getTypeListWithPage(TypeQuery typeQuery) {
		int total = typeDao.getTypeListCount(typeQuery);
		if (total > 0){
			List<Type> list = typeDao.getTypeListWithPage(typeQuery);
			return new PageResult<Type>(total, typeQuery.getPageSize(), list);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Type> getTypeList(TypeQuery typeQuery) {
		return typeDao.getTypeList(typeQuery);
	}
}
