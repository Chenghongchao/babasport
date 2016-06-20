package com.lionxxw.babasport.country.impl;

import com.lionxxw.babasport.country.dao.CityDao;
import com.lionxxw.babasport.country.dto.CityDto;
import com.lionxxw.babasport.country.entity.City;
import com.lionxxw.babasport.country.service.CityService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtils;
import com.lionxxw.common.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 市接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 上午12:03
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    public CityDto save(CityDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        City city = BeanUtils.createBeanByTarget(obj, City.class);
        cityDao.insertSelective(city);
        obj.setId(city.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, City.class, "delById");
        int i = cityDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(CityDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), City.class, "update");
        City city = BeanUtils.createBeanByTarget(obj, City.class);
        cityDao.updateByPrimaryKeySelective(city);
    }

    public CityDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, City.class, "getById");
        City city = cityDao.selectByPrimaryKey(id);
        CityDto dto = BeanUtils.createBeanByTarget(city, CityDto.class);
        return dto;
    }

    public List<CityDto> queryByParam(CityDto obj) throws Exception {
        List<City> citys = cityDao.queryByParam(obj, null);
        if (null != citys && citys.size() > 0){
            List<CityDto> list = BeanUtils.createBeanListByTarget(citys, CityDto.class);
            return list;
        }
        return null;
    }

    public PageResult<CityDto> queryByPage(CityDto obj, PageQuery query) throws Exception {
        int total = cityDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<City> citys = cityDao.queryByParam(obj, query);
            List<CityDto> list = BeanUtils.createBeanListByTarget(citys, CityDto.class);
            return new PageResult<CityDto>(query, list);
        }
        return null;
    }

    public List<CityDto> queryByProvince(String province) throws Exception {
        CityDto dto = new CityDto();
        dto.setProvince(province);
        return queryByParam(dto);
    }
}
