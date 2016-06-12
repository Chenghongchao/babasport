package com.lionxxw.babasport.core.service.product.impl;

import java.util.List;

import javax.annotation.Resource;

import com.javafx.tools.doclets.internal.toolkit.taglets.TagletOutput;
import com.lionxxw.babasport.core.dao.product.SkuDao;
import com.lionxxw.babasport.core.dto.product.Product;
import com.lionxxw.babasport.core.dto.product.Sku;
import com.lionxxw.babasport.core.query.product.SkuQuery;
import com.lionxxw.babasport.core.service.product.ColorService;
import com.lionxxw.babasport.core.service.product.ProductService;
import com.lionxxw.babasport.core.service.product.SkuService;
import com.lionxxw.common.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 最小销售单元事务层
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class SkuServiceImpl implements SkuService {

	@Resource
	SkuDao skuDao;
	@Resource
	ColorService colorService;
	@Resource
	ProductService productService;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addSku(Sku sku) {
		return skuDao.addSku(sku);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Sku getSkuByKey(Integer id) {
		Sku sku = skuDao.getSkuByKey(id);
		//通过商品ID
		Product product = productService.getProductByKey(sku.getProductId());
		
		sku.setProduct(product);
		//颜色加载
		sku.setColor(colorService.getColorByKey(sku.getColorId()));
		
		return sku;
	}
	
	@Transactional(readOnly = true)
	public List<Sku> getSkusByKeys(List<Integer> idList) {
		return skuDao.getSkusByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return skuDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return skuDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateSkuByKey(Sku sku) {
		return skuDao.updateSkuByKey(sku);
	}
	
	@Transactional(readOnly = true)
	public PageResult<Sku> getSkuListWithPage(SkuQuery skuQuery) {
		int total = skuDao.getSkuListCount(skuQuery);
		if (total > 0){
			List<Sku> list = skuDao.getSkuListWithPage(skuQuery);
			return new PageResult<Sku>(total, skuQuery.getPageSize(), list);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Sku> getSkuList(SkuQuery skuQuery) {
		List<Sku> skus = skuDao.getSkuList(skuQuery);
		//颜色加载完结
		for(Sku sku : skus){
			sku.setColor(colorService.getColorByKey(sku.getColorId()));
		}
		return skus;
	}

	public List<Sku> getStock(Integer productId) {
		// TODO Auto-generated method stub
		List<Sku> skus = skuDao.getStock(productId);
		//颜色加载完结
		for(Sku sku : skus){
			sku.setColor(colorService.getColorByKey(sku.getColorId()));
		}
		return skus;
	}
}
