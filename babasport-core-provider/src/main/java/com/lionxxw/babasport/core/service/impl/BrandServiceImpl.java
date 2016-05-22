package com.lionxxw.babasport.core.service.impl;

import com.lionxxw.babasport.core.dao.BrandDao;
import com.lionxxw.babasport.core.dto.BrandDto;
import com.lionxxw.babasport.core.entity.Brand;
import com.lionxxw.babasport.core.service.BrandService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtil;
import com.lionxxw.common.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 品牌接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 上午12:03
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    public BrandDto save(BrandDto obj) throws Exception {
        ExceptionUtil.checkObjIsNull(obj);
        Brand brand = BeanUtil.createBeanByTarget(obj, Brand.class);
        brandDao.insertSelective(brand);
        obj.setId(brand.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtil.checkIdIsNull(id, Brand.class, "delById");
        int i = brandDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(BrandDto obj) throws Exception {
        ExceptionUtil.checkObjIsNull(obj);
        ExceptionUtil.checkIdIsNull(obj.getId(), Brand.class, "update");
        Brand brand = BeanUtil.createBeanByTarget(obj, Brand.class);
        brandDao.updateByPrimaryKeySelective(brand);
    }

    public BrandDto getById(Integer id) throws Exception {
        ExceptionUtil.checkIdIsNull(id, Brand.class, "getById");
        Brand brand = brandDao.selectByPrimaryKey(id);
        BrandDto dto = BeanUtil.createBeanByTarget(brand, BrandDto.class);
        return dto;
    }

    public List<BrandDto> queryByParam(BrandDto obj) throws Exception {
        List<Brand> brands = brandDao.queryByParam(obj, null);
        if (null != brands && brands.size() > 0){
            List<BrandDto> list = BeanUtil.createBeanListByTarget(brands, BrandDto.class);
            return list;
        }
        return null;
    }

    public PageResult<BrandDto> queryByPage(BrandDto obj, PageQuery query) throws Exception {
        int total = brandDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Brand> brands = brandDao.queryByParam(obj, query);
            List<BrandDto> list = BeanUtil.createBeanListByTarget(brands, BrandDto.class);
            return new PageResult<BrandDto>(query, list);
        }
        return null;
    }
}
