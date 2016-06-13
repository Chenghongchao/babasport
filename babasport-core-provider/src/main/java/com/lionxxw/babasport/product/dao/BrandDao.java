package com.lionxxw.babasport.product.dao;

import com.lionxxw.babasport.product.dto.BrandDto;
import com.lionxxw.babasport.product.entity.Brand;
import com.lionxxw.babasport.product.entity.BrandExample;
import com.lionxxw.babasport.product.mapper.BrandMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtils;
import com.lionxxw.common.utils.StringUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 品牌dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:35
 */
@Repository
public class BrandDao extends MyBatisBaseDao<Brand> {

    @Autowired
    @Getter
    private BrandMapper mapper;

    public List<Brand> queryByParam(BrandDto obj, PageQuery query) throws Exception{
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("sort desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("sort desc");
        }
        List<Brand> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(BrandDto obj) throws Exception{
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(BrandDto params, BrandExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (StringUtils.notTrimEmpty(params.getName())){
                criteria.andNameEqualTo(params.getName().trim());
            }
            if (null != params.getIsDisplay()){
                criteria.andIsDisplayEqualTo(params.getIsDisplay());
            }
        }
    }
}
