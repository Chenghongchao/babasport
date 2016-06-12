package com.lionxxw.babasport.core.service.user.impl;

import com.lionxxw.babasport.core.dao.user.BuyerDao;
import com.lionxxw.babasport.core.dto.user.Buyer;
import com.lionxxw.babasport.core.query.user.BuyerQuery;
import com.lionxxw.babasport.core.service.user.BuyerService;
import com.lionxxw.common.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
/**
 * 购买者
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

	@Resource
	BuyerDao buyerDao;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addBuyer(Buyer buyer) {
		return buyerDao.addBuyer(buyer);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Buyer getBuyerByKey(String id) {
		return buyerDao.getBuyerByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Buyer> getBuyersByKeys(List<String> idList) {
		return buyerDao.getBuyersByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(String id) {
		return buyerDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<String> idList) {
		return buyerDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateBuyerByKey(Buyer buyer) {
		return buyerDao.updateBuyerByKey(buyer);
	}
	
	@Transactional(readOnly = true)
	public PageResult<Buyer> getBuyerListWithPage(BuyerQuery buyerQuery) {
		int total = buyerDao.getBuyerListCount(buyerQuery);
		if (total > 0){
			List<Buyer> list = buyerDao.getBuyerListWithPage(buyerQuery);
			return new PageResult<Buyer>(total, buyerQuery.getPageSize(),list);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Buyer> getBuyerList(BuyerQuery buyerQuery) {
		return buyerDao.getBuyerList(buyerQuery);
	}
}
