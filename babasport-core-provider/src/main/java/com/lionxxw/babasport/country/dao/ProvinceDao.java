package com.lionxxw.babasport.country.dao;

import com.lionxxw.babasport.country.dto.ProvinceDto;
import com.lionxxw.babasport.country.entity.Province;
import com.lionxxw.babasport.country.entity.ProvinceExample;
import com.lionxxw.babasport.country.mapper.ProvinceMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtils;
import com.lionxxw.common.utils.StringUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 省dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:35
 */
@Repository
public class ProvinceDao extends MyBatisBaseDao<Province> {

    @Autowired
    @Getter
    private ProvinceMapper mapper;

    public List<Province> queryByParam(ProvinceDto obj, PageQuery query) throws Exception{
        ProvinceExample example = new ProvinceExample();
        ProvinceExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("code desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("code desc");
        }
        List<Province> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(ProvinceDto obj) throws Exception{
        ProvinceExample example = new ProvinceExample();
        ProvinceExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(ProvinceDto params, ProvinceExample.Criteria criteria) {
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
        }
    }
}
