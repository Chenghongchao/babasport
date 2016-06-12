package com.lionxxw.babasport.core.dao;

import com.lionxxw.babasport.core.dto.product.ProductTypeDto;
import com.lionxxw.babasport.core.entity.ProductType;
import com.lionxxw.babasport.core.entity.ProductTypeExample;
import com.lionxxw.babasport.core.mapper.ProductTypeMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtils;
import com.lionxxw.common.utils.StringUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 商品类型dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:35
 */
@Repository
public class ProductTypeDao extends MyBatisBaseDao<ProductType> {

    @Autowired
    @Getter
    private ProductTypeMapper mapper;

    public List<ProductType> queryByParam(ProductTypeDto obj, PageQuery query) throws Exception{
        ProductTypeExample example = new ProductTypeExample();
        ProductTypeExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("is_display desc,id asc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("is_display desc,id asc");
        }
        List<ProductType> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(ProductTypeDto obj) throws Exception{
        ProductTypeExample example = new ProductTypeExample();
        ProductTypeExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(ProductTypeDto params, ProductTypeExample.Criteria criteria) {
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
