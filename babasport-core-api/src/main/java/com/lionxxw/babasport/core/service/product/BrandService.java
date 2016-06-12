package com.lionxxw.babasport.core.service.product;

import java.util.List;

import com.lionxxw.babasport.core.dto.product.Brand;
import com.lionxxw.babasport.core.query.product.BrandQuery;
import com.lionxxw.common.model.PageResult;

/**
 * 品牌
 * @author lx
 *
 */
public interface BrandService {

	PageResult<Brand> getBrandListWithPage(Brand brand);
	
	//查询集合
	List<Brand> getBrandList(BrandQuery brandQuery);
	
	//添加品牌
	void addBrand(Brand brand);
	
	//删除
	void deleteBrandByKey(Integer id);
	//删除 批量
	void deleteBrandByKeys(Integer[] ids);//List<Integer>  ids
	//修改
	void updateBrandByKey(Brand brand);
	
	//
	Brand getBrandByKey(Integer id);
}
