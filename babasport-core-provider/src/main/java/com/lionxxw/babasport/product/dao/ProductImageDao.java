package com.lionxxw.babasport.product.dao;

import com.lionxxw.babasport.product.dto.ProductImageDto;
import com.lionxxw.babasport.product.entity.ProductImage;
import com.lionxxw.babasport.product.entity.ProductImageExample;
import com.lionxxw.babasport.product.mapper.ProductImageMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 商品图片dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:35
 */
@Repository
public class ProductImageDao extends MyBatisBaseDao<ProductImage> {

    @Autowired
    @Getter
    private ProductImageMapper mapper;

    public List<ProductImage> queryByParam(ProductImageDto obj, PageQuery query) throws Exception{
        ProductImageExample example = new ProductImageExample();
        ProductImageExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("is_def desc,id asc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("is_def desc,id asc");
        }
        List<ProductImage> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(ProductImageDto obj) throws Exception{
        ProductImageExample example = new ProductImageExample();
        ProductImageExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(ProductImageDto params, ProductImageExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (ObjectUtils.notNull(params.getProductId())){
                criteria.andProductIdEqualTo(params.getProductId());
            }
            if (null != params.getIsDef()){
                criteria.andIsDefEqualTo(params.getIsDef());
            }
        }

    }
}
