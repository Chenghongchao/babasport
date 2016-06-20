package com.lionxxw.babasport.country.dao;

import com.lionxxw.babasport.country.dto.TownDto;
import com.lionxxw.babasport.country.entity.Town;
import com.lionxxw.babasport.country.entity.TownExample;
import com.lionxxw.babasport.country.mapper.TownMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtils;
import com.lionxxw.common.utils.StringUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 县/区dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:35
 */
@Repository
public class TownDao extends MyBatisBaseDao<Town> {

    @Autowired
    @Getter
    private TownMapper mapper;

    public List<Town> queryByParam(TownDto obj, PageQuery query) throws Exception{
        TownExample example = new TownExample();
        TownExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("code desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("code desc");
        }
        List<Town> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(TownDto obj) throws Exception{
        TownExample example = new TownExample();
        TownExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(TownDto params, TownExample.Criteria criteria) {
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
            if (StringUtils.notTrimEmpty(params.getCity())){
                criteria.andCityEqualTo(params.getCity().trim());
            }
        }
    }
}
