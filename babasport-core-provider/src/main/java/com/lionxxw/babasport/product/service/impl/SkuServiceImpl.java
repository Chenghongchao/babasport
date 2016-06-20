package com.lionxxw.babasport.product.service.impl;

import com.lionxxw.babasport.product.dao.SkuDao;
import com.lionxxw.babasport.product.dto.SkuDto;
import com.lionxxw.babasport.product.entity.Sku;
import com.lionxxw.babasport.product.service.ProductService;
import com.lionxxw.babasport.product.service.SkuService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtils;
import com.lionxxw.common.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 商品库存接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 上午12:03
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuDao skuDao;
    @Autowired
    private ProductService productService;

    public SkuDto save(SkuDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        Sku sku = BeanUtils.createBeanByTarget(obj, Sku.class);
        skuDao.insertSelective(sku);
        obj.setId(sku.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Sku.class, "delById");
        int i = skuDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(SkuDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), Sku.class, "update");
        Sku sku = BeanUtils.createBeanByTarget(obj, Sku.class);
        skuDao.updateByPrimaryKeySelective(sku);
    }

    public SkuDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Sku.class, "getById");
        Sku sku = skuDao.selectByPrimaryKey(id);
        SkuDto dto = BeanUtils.createBeanByTarget(sku, SkuDto.class);
        dto.setProduct(productService.getById(sku.getProductId()));
        return dto;
    }

    public List<SkuDto> queryByParam(SkuDto obj) throws Exception {
        List<Sku> skus = skuDao.queryByParam(obj, null);
        if (null != skus && skus.size() > 0){
            List<SkuDto> list = BeanUtils.createBeanListByTarget(skus, SkuDto.class);
            return list;
        }
        return null;
    }

    public PageResult<SkuDto> queryByPage(SkuDto obj, PageQuery query) throws Exception {
        int total = skuDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Sku> skus = skuDao.queryByParam(obj, query);
            List<SkuDto> list = BeanUtils.createBeanListByTarget(skus, SkuDto.class);
            return new PageResult<SkuDto>(query, list);
        }
        return null;
    }

    public List<SkuDto> queryInventory(Integer productId) throws Exception{
        SkuDto params = new SkuDto();
        params.setProductId(productId);
        params.setStockInventory(0);
        List<Sku> skus = skuDao.queryByParam(params, null);
        if (null != skus && skus.size() > 0){
            List<SkuDto> list = BeanUtils.createBeanListByTarget(skus, SkuDto.class);
            return list;
        }
        return null;
    }
}
