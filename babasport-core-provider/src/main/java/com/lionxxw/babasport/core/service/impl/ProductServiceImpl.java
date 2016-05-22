package com.lionxxw.babasport.core.service.impl;

import com.lionxxw.babasport.core.dao.ProductDao;
import com.lionxxw.babasport.core.dto.ProductDto;
import com.lionxxw.babasport.core.entity.Product;
import com.lionxxw.babasport.core.service.ProductService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtil;
import com.lionxxw.common.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 产品接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:50
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public ProductDto save(ProductDto obj) throws Exception {
        ExceptionUtil.checkObjIsNull(obj);
        Product product = BeanUtil.createBeanByTarget(obj, Product.class);
        productDao.insertSelective(product);
        obj.setId(product.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtil.checkIdIsNull(id, Product.class, "delById");
        int i = productDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(ProductDto obj) throws Exception {
        ExceptionUtil.checkObjIsNull(obj);
        ExceptionUtil.checkIdIsNull(obj.getId(), Product.class, "update");
        Product product = BeanUtil.createBeanByTarget(obj, Product.class);
        productDao.updateByPrimaryKeySelective(product);
    }

    public ProductDto getById(Integer id) throws Exception {
        ExceptionUtil.checkIdIsNull(id, Product.class, "getById");
        Product product = productDao.selectByPrimaryKey(id);
        ProductDto dto = BeanUtil.createBeanByTarget(product, ProductDto.class);
        return dto;
    }

    public List<ProductDto> queryByParam(ProductDto obj) throws Exception {
        List<Product> products = productDao.queryByParam(obj, null);
        if (null != products && products.size() > 0){
            List<ProductDto> list = BeanUtil.createBeanListByTarget(products, ProductDto.class);
            return list;
        }
        return null;
    }

    public PageResult<ProductDto> queryByPage(ProductDto obj, PageQuery query) throws Exception {
        int total = productDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Product> products = productDao.queryByParam(obj, query);
            List<ProductDto> list = BeanUtil.createBeanListByTarget(products, ProductDto.class);
            return new PageResult<ProductDto>(query, list);
        }
        return null;
    }
}
