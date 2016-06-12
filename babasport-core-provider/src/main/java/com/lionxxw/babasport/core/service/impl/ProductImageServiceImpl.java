package com.lionxxw.babasport.core.service.impl;

import com.lionxxw.babasport.core.dao.ProductImageDao;
import com.lionxxw.babasport.core.dto.product.ProductImageDto;
import com.lionxxw.babasport.core.entity.ProductImage;
import com.lionxxw.babasport.core.service.ProductImageService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtils;
import com.lionxxw.common.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 商品图片接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 上午12:03
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageDao productImageDao;

    public ProductImageDto save(ProductImageDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ProductImage image = BeanUtils.createBeanByTarget(obj, ProductImage.class);
        productImageDao.insertSelective(image);
        obj.setId(image.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, ProductImage.class, "delById");
        int i = productImageDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(ProductImageDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), ProductImage.class, "update");
        ProductImage image = BeanUtils.createBeanByTarget(obj, ProductImage.class);
        productImageDao.updateByPrimaryKeySelective(image);
    }

    public ProductImageDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, ProductImage.class, "getById");
        ProductImage image = productImageDao.selectByPrimaryKey(id);
        ProductImageDto dto = BeanUtils.createBeanByTarget(image, ProductImageDto.class);
        return dto;
    }

    public List<ProductImageDto> queryByParam(ProductImageDto obj) throws Exception {
        List<ProductImage> ProductImages = productImageDao.queryByParam(obj, null);
        if (null != ProductImages && ProductImages.size() > 0){
            List<ProductImageDto> list = BeanUtils.createBeanListByTarget(ProductImages, ProductImageDto.class);
            return list;
        }
        return null;
    }

    public PageResult<ProductImageDto> queryByPage(ProductImageDto obj, PageQuery query) throws Exception {
        int total = productImageDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<ProductImage> images = productImageDao.queryByParam(obj, query);
            List<ProductImageDto> list = BeanUtils.createBeanListByTarget(images, ProductImageDto.class);
            return new PageResult<ProductImageDto>(query, list);
        }
        return null;
    }
}
