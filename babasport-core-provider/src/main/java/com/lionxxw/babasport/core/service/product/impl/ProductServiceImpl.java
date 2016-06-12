package com.lionxxw.babasport.core.service.product.impl;

import com.lionxxw.babasport.core.dao.product.ProductDao;
import com.lionxxw.babasport.core.dao.product.ProductImageDao;
import com.lionxxw.babasport.core.dao.product.SkuDao;
import com.lionxxw.babasport.core.dto.product.ProductDto;
import com.lionxxw.babasport.core.entity.Product;
import com.lionxxw.babasport.core.entity.ProductImage;
import com.lionxxw.babasport.core.entity.ProductWithBLOBs;
import com.lionxxw.babasport.core.entity.Sku;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtils;
import com.lionxxw.common.utils.ExceptionUtils;
import com.lionxxw.common.utils.UploadImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    @Autowired
    private ProductImageDao imageDao;
    @Autowired
    private SkuDao skuDao;

    @Transactional
    public ProductDto save(ProductDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        obj.setNo(UploadImageUtils.getCreateFormat());
        obj.setCreateUserId("后台管理员");
        obj.setCreateTime(new Date());
        ProductWithBLOBs product = BeanUtils.createBeanByTarget(obj, ProductWithBLOBs.class);
        productDao.insertSelective(product);
        ProductImage image = new ProductImage();
        obj.setId(product.getId());
        image.setProductId(product.getId());
        if (null != obj.getImage()){
            image.setUrl(obj.getImage().getUrl());
        }
        imageDao.insertSelective(image);

        Sku sku = new Sku();
        sku.setProductId(product.getId());
        sku.setSkuPrice(0.00);
        sku.setStockInventory(0);
        sku.setStockUpperLimit(1);
        sku.setLocation("");
        sku.setMarketPrice(0.00);
        sku.setCreateTime(new Date());
        sku.setLastStatus(1);
        sku.setSkuType(1);
        sku.setSales(0);
        sku.setCreateUserId("后台管理员");
        for(String colorId : product.getColor().split(",")){
            sku.setColorId(Integer.parseInt(colorId));
            for(String size : product.getSize().split(",")){
                sku.setSizeId(Integer.parseInt(size));
                skuDao.insertSelective(sku);
            }
        }

        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Product.class, "delById");
        int i = productDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(ProductDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), Product.class, "update");
        ProductWithBLOBs product = BeanUtils.createBeanByTarget(obj, ProductWithBLOBs.class);
        productDao.updateByPrimaryKeySelective(product);
    }

    public ProductDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Product.class, "getById");
        ProductWithBLOBs product = productDao.selectByPrimaryKey(id);
        ProductDto dto = BeanUtils.createBeanByTarget(product, ProductDto.class);
        return dto;
    }

    public List<ProductDto> queryByParam(ProductDto obj) throws Exception {
        List<Product> products = productDao.queryByParam(obj, null);
        if (null != products && products.size() > 0){
            List<ProductDto> list = BeanUtils.createBeanListByTarget(products, ProductDto.class);
            return list;
        }
        return null;
    }

    public PageResult<ProductDto> queryByPage(ProductDto obj, PageQuery query) throws Exception {
        int total = productDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Product> products = productDao.queryByParam(obj, query);
            List<ProductDto> list = BeanUtils.createBeanListByTarget(products, ProductDto.class);
            return new PageResult<ProductDto>(query, list);
        }
        return null;
    }
}
