package com.lionxxw.babasport.core.dao.product;

import com.lionxxw.babasport.core.dto.product.Type;
import com.lionxxw.babasport.core.query.product.TypeQuery;

import java.util.List;


public interface TypeDao {

	/**
	 * 添加
	 * @param type
	 */
	public Integer addType(Type type);

	/**
	 * 根据主键查找
	 */
	public Type getTypeByKey(Integer id);

	/**
	 * 根据主键批量查找
	 */
	public List<Type> getTypesByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 */
	public Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 */
	public Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 */
	public Integer updateTypeByKey(Type type);

	/**
	 * 分页查询
	 * @param typeQuery
	 */
	public List<Type> getTypeListWithPage(TypeQuery typeQuery);

	/**
	 * 集合查询
	 * @param typeQuery
	 */
	public List<Type> getTypeList(TypeQuery typeQuery);
	
	/**
	 * 总条数
	 * @param typeQuery
	 */
	public int getTypeListCount(TypeQuery typeQuery);
}
