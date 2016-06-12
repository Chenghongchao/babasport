package com.lionxxw.babasport.core.service.user;


import com.lionxxw.babasport.core.dto.user.Buyer;
import com.lionxxw.babasport.core.query.user.BuyerQuery;
import com.lionxxw.common.model.PageResult;

import java.util.List;

/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午01:50:28]
 */
public interface BuyerService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addBuyer(Buyer buyer);

	/**
	 * 根据主键查询
	 */
	Buyer getBuyerByKey(String id);

	/**
	 * 根据主键批量查询
	 */
	List<Buyer> getBuyersByKeys(List<String> idList);

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	Integer deleteByKey(String id);

	/**
	 * 根据主键批量删除
	 * 
	 * @return
	 */
	Integer deleteByKeys(List<String> idList);

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	Integer updateBuyerByKey(Buyer buyer);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param buyerQuery
	 *            查询条件
	 * @return
	 */
	PageResult<Buyer> getBuyerListWithPage(BuyerQuery buyerQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param buyerQuery
	 *            查询条件
	 * @return
	 */
	List<Buyer> getBuyerList(BuyerQuery buyerQuery);
}
