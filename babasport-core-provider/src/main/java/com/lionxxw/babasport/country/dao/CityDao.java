package com.lionxxw.babasport.country.dao;

import com.lionxxw.babasport.country.dto.CityDto;
import com.lionxxw.babasport.country.entity.City;
import com.lionxxw.babasport.country.entity.CityExample;
import com.lionxxw.babasport.country.mapper.CityMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtils;
import com.lionxxw.common.utils.StringUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 市dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:35
 */
@Repository
public class CityDao extends MyBatisBaseDao<City> {

    @Autowired
    @Getter
    private CityMapper mapper;

    public List<City> queryByParam(CityDto obj, PageQuery query) throws Exception{
        CityExample example = new CityExample();
        CityExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("code desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("code desc");
        }
        List<City> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(CityDto obj) throws Exception{
        CityExample example = new CityExample();
        CityExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(CityDto params, CityExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (StringUtils.notTrimEmpty(params.getName())){
                criteria.andNameEqualTo(params.getName().trim());
            }
            if (StringUtils.notTrimEmpty(params.getCode())){
                criteria.andCodeEqualTo(params.getCode().trim());
            }
            if (StringUtils.notTrimEmpty(params.getProvince())){
                criteria.andProvinceEqualTo(params.getProvince().trim());
            }
        }
    }
}
