package com.lionxxw.babasport.product.service.impl;

import com.lionxxw.babasport.product.dao.ProductDao;
import com.lionxxw.babasport.product.dao.ProductImageDao;
import com.lionxxw.babasport.product.dao.SkuDao;
import com.lionxxw.babasport.product.dto.ProductDto;
import com.lionxxw.babasport.product.entity.Product;
import com.lionxxw.babasport.product.entity.ProductImage;
import com.lionxxw.babasport.product.entity.ProductWithBLOBs;
import com.lionxxw.babasport.product.entity.Sku;
import com.lionxxw.babasport.product.service.ProductService;
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
        image.setUrl(obj.getImageUrl());
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
        ProductDto obj = new ProductDto();
        obj.setId(id);
        obj.setIsDel(false);
        List<ProductDto> productDtos = queryByParam(obj);
        if (null != productDtos && productDtos.size() > 0){
            return productDtos.get(0);
        }
       return  null;
    }

    public List<ProductDto> queryByParam(ProductDto obj) throws Exception {
        return productDao.queryByParam(obj, null);
        /*List<Product> products = productDao.queryByParam(obj, null);
        if (null != products && products.size() > 0){
            List<ProductDto> list = BeanUtils.createBeanListByTarget(products, ProductDto.class);
            return list;
        }
        return null;*/
    }

    public PageResult<ProductDto> queryByPage(ProductDto obj, PageQuery query) throws Exception {
        int total = productDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            /*List<Product> products = productDao.queryByParam(obj, query);
            List<ProductDto> list = BeanUtils.createBeanListByTarget(products, ProductDto.class);*/
            List<ProductDto> list = productDao.queryByParam(obj, query);
            return new PageResult<ProductDto>(query, list);
        }
        return null;
    }
}
