package com.lionxxw.babasport.country.impl;

import com.lionxxw.babasport.country.dao.ProvinceDao;
import com.lionxxw.babasport.country.dto.ProvinceDto;
import com.lionxxw.babasport.country.entity.Province;
import com.lionxxw.babasport.country.service.ProvinceService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtils;
import com.lionxxw.common.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 省接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 上午12:03
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    public ProvinceDto save(ProvinceDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        Province province = BeanUtils.createBeanByTarget(obj, Province.class);
        provinceDao.insertSelective(province);
        obj.setId(province.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Province.class, "delById");
        int i = provinceDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(ProvinceDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), Province.class, "update");
        Province province = BeanUtils.createBeanByTarget(obj, Province.class);
        provinceDao.updateByPrimaryKeySelective(province);
    }

    public ProvinceDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Province.class, "getById");
        Province province = provinceDao.selectByPrimaryKey(id);
        ProvinceDto dto = BeanUtils.createBeanByTarget(province, ProvinceDto.class);
        return dto;
    }

    public List<ProvinceDto> queryByParam(ProvinceDto obj) throws Exception {
        List<Province> provinces = provinceDao.queryByParam(obj, null);
        if (null != provinces && provinces.size() > 0){
            List<ProvinceDto> list = BeanUtils.createBeanListByTarget(provinces, ProvinceDto.class);
            return list;
        }
        return null;
    }

    public PageResult<ProvinceDto> queryByPage(ProvinceDto obj, PageQuery query) throws Exception {
        int total = provinceDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Province> provinces = provinceDao.queryByParam(obj, query);
            List<ProvinceDto> list = BeanUtils.createBeanListByTarget(provinces, ProvinceDto.class);
            return new PageResult<ProvinceDto>(query, list);
        }
        return null;
    }
}
