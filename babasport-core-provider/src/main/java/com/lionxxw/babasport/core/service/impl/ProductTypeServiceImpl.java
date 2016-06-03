package com.lionxxw.babasport.core.service.impl;

import com.lionxxw.babasport.core.dao.ProductTypeDao;
import com.lionxxw.babasport.core.dto.ProductTypeDto;
import com.lionxxw.babasport.core.entity.ProductType;
import com.lionxxw.babasport.core.service.ProductTypeService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtils;
import com.lionxxw.common.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 商品类型接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 上午12:03
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeDao productTypeDao;

    public ProductTypeDto save(ProductTypeDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ProductType productType = BeanUtils.createBeanByTarget(obj, ProductType.class);
        productTypeDao.insertSelective(productType);
        obj.setId(productType.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, ProductType.class, "delById");
        int i = productTypeDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(ProductTypeDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), ProductType.class, "update");
        ProductType productType = BeanUtils.createBeanByTarget(obj, ProductType.class);
        productTypeDao.updateByPrimaryKeySelective(productType);
    }

    public ProductTypeDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, ProductType.class, "getById");
        ProductType productType = productTypeDao.selectByPrimaryKey(id);
        ProductTypeDto dto = BeanUtils.createBeanByTarget(productType, ProductTypeDto.class);
        return dto;
    }

    public List<ProductTypeDto> queryByParam(ProductTypeDto obj) throws Exception {
        List<ProductType> productTypes = productTypeDao.queryByParam(obj, null);
        if (null != productTypes && productTypes.size() > 0){
            List<ProductTypeDto> list = BeanUtils.createBeanListByTarget(productTypes, ProductTypeDto.class);
            return list;
        }
        return null;
    }

    public PageResult<ProductTypeDto> queryByPage(ProductTypeDto obj, PageQuery query) throws Exception {
        int total = productTypeDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<ProductType> productTypes = productTypeDao.queryByParam(obj, query);
            List<ProductTypeDto> list = BeanUtils.createBeanListByTarget(productTypes, ProductTypeDto.class);
            return new PageResult<ProductTypeDto>(query, list);
        }
        return null;
    }
}
