package com.lionxxw.babasport.country.impl;

import com.lionxxw.babasport.country.dao.TownDao;
import com.lionxxw.babasport.country.dto.TownDto;
import com.lionxxw.babasport.country.entity.Town;
import com.lionxxw.babasport.country.service.TownService;
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
public class TownServiceImpl implements TownService {

    @Autowired
    private TownDao townDao;

    public TownDto save(TownDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        Town town = BeanUtils.createBeanByTarget(obj, Town.class);
        townDao.insertSelective(town);
        obj.setId(town.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Town.class, "delById");
        int i = townDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(TownDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), Town.class, "update");
        Town town = BeanUtils.createBeanByTarget(obj, Town.class);
        townDao.updateByPrimaryKeySelective(town);
    }

    public TownDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Town.class, "getById");
        Town town = townDao.selectByPrimaryKey(id);
        TownDto dto = BeanUtils.createBeanByTarget(town, TownDto.class);
        return dto;
    }

    public List<TownDto> queryByParam(TownDto obj) throws Exception {
        List<Town> towns = townDao.queryByParam(obj, null);
        if (null != towns && towns.size() > 0){
            List<TownDto> list = BeanUtils.createBeanListByTarget(towns, TownDto.class);
            return list;
        }
        return null;
    }

    public PageResult<TownDto> queryByPage(TownDto obj, PageQuery query) throws Exception {
        int total = townDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Town> towns = townDao.queryByParam(obj, query);
            List<TownDto> list = BeanUtils.createBeanListByTarget(towns, TownDto.class);
            return new PageResult<TownDto>(query, list);
        }
        return null;
    }

    public List<TownDto> queryByCity(String city) throws Exception {
        TownDto dto = new TownDto();
        dto.setCity(city);
        return queryByParam(dto);
    }
}
