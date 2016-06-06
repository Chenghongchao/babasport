package com.lionxxw.babasport.core.dao;

import com.lionxxw.babasport.core.dto.MaterialDto;
import com.lionxxw.babasport.core.entity.Material;
import com.lionxxw.babasport.core.entity.MaterialExample;
import com.lionxxw.babasport.core.mapper.MaterialMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtils;
import com.lionxxw.common.utils.StringUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 颜色dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:35
 */
@Repository
public class MaterialDao extends MyBatisBaseDao<Material> {

    @Autowired
    @Getter
    private MaterialMapper mapper;

    public List<Material> queryByParam(MaterialDto obj, PageQuery query) throws Exception{
        MaterialExample example = new MaterialExample();
        MaterialExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("is_display desc,id asc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("is_display desc,id asc");
        }
        List<Material> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(MaterialDto obj) throws Exception{
        MaterialExample example = new MaterialExample();
        MaterialExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(MaterialDto params, MaterialExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (ObjectUtils.notNull(params.getParentId())){
                criteria.andParentIdEqualTo(params.getParentId());
            }
            if (StringUtils.notTrimEmpty(params.getName())){
                criteria.andNameEqualTo(params.getName().trim());
            }
            if (null != params.getIsDisplay()){
                criteria.andIsDisplayEqualTo(params.getIsDisplay());
            }else{
                criteria.andIsDisplayEqualTo(true);
            }
        }else{
            criteria.andIsDisplayEqualTo(true);
        }

    }
}
