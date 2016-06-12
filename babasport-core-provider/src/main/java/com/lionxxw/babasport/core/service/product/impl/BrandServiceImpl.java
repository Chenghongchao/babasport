package com.lionxxw.babasport.core.service.product.impl;

import java.util.List;

import javax.annotation.Resource;

import com.lionxxw.babasport.core.dao.product.BrandDao;
import com.lionxxw.babasport.core.dto.product.Brand;
import com.lionxxw.babasport.core.query.product.BrandQuery;
import com.lionxxw.babasport.core.service.product.BrandService;
import com.lionxxw.common.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 品牌事务
 * @author lx
 *
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {
	
	@Resource
	private BrandDao brandDao;

	@Transactional(readOnly = true)
	public PageResult<Brand> getBrandListWithPage(Brand brand){
		//1:起始页  startRow = (pageNo - 1)*pageSize
		//2:每页数
		//3:总记录数
		int total = brandDao.getBrandCount(brand);
		if (total > 0){
			List<Brand> list = brandDao.getBrandListWithPage(brand);
			return new PageResult<Brand>(total, brand.getPageSize(), list);
		}
		return null;
	}

	public void addBrand(Brand brand) {
		brandDao.addBrand(brand);
	}

	public void deleteBrandByKey(Integer id) {
		brandDao.deleteBrandByKey(id);
		
	}

	public void deleteBrandByKeys(Integer[] ids) {
		brandDao.deleteBrandByKeys(ids);
		
	}

	public void updateBrandByKey(Brand brand) {
		brandDao.updateBrandByKey(brand);
		
	}

	public Brand getBrandByKey(Integer id) {
		// TODO Auto-generated method stub
		return brandDao.getBrandByKey(id);
	}

	public List<Brand> getBrandList(BrandQuery brandQuery) {
		// TODO Auto-generated method stub
		return brandDao.getBrandList(brandQuery);
	}
}
