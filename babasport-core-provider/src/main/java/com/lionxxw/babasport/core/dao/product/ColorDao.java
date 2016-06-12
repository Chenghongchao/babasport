package com.lionxxw.babasport.core.dao.product;

import com.lionxxw.babasport.core.dto.product.ColorDto;
import com.lionxxw.babasport.core.entity.Color;
import com.lionxxw.babasport.core.entity.ColorExample;
import com.lionxxw.babasport.core.mapper.ColorMapper;
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
public class ColorDao extends MyBatisBaseDao<Color> {

    @Autowired
    @Getter
    private ColorMapper mapper;

    public List<Color> queryByParam(ColorDto obj, PageQuery query) throws Exception{
        ColorExample example = new ColorExample();
        ColorExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("is_display desc,id asc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("is_display desc,id asc");
        }
        List<Color> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(ColorDto obj) throws Exception{
        ColorExample example = new ColorExample();
        ColorExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(ColorDto params, ColorExample.Criteria criteria) {
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
