package com.lionxxw.babasport.core.service.product;

import com.lionxxw.babasport.core.dto.product.Sku;
import com.lionxxw.babasport.core.query.product.SkuQuery;
import com.lionxxw.common.model.PageResult;

import java.util.List;


/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午01:50:28]
 */
public interface SkuService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addSku(Sku sku);

	/**
	 * 根据主键查询
	 */
	Sku getSkuByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	List<Sku> getSkusByKeys(List<Integer> idList);

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
	Integer updateSkuByKey(Sku sku);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param skuQuery
	 *            查询条件
	 * @return
	 */
	PageResult<Sku> getSkuListWithPage(SkuQuery skuQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param skuQuery
	 *            查询条件
	 * @return
	 */
	List<Sku> getSkuList(SkuQuery skuQuery);
	
	/**
	 * 库存大于>0
	 */
	List<Sku> getStock(Integer productId);
	
}
