package com.lionxxw.babasport.core.dao;

import com.lionxxw.babasport.core.dto.SkuDto;
import com.lionxxw.babasport.core.entity.Sku;
import com.lionxxw.babasport.core.entity.SkuExample;
import com.lionxxw.babasport.core.mapper.SkuMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 商品库存dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:35
 */
@Repository
public class SkuDao extends MyBatisBaseDao<Sku> {

    @Autowired
    @Getter
    private SkuMapper mapper;

    public List<Sku> queryByParam(SkuDto obj, PageQuery query) throws Exception{
        SkuExample example = new SkuExample();
        SkuExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("create_time desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("create_time desc");
        }
        List<Sku> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(SkuDto obj) throws Exception{
        SkuExample example = new SkuExample();
        SkuExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(SkuDto params, SkuExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (ObjectUtils.notNull(params.getProductId())){
                criteria.andProductIdEqualTo(params.getProductId());
            }
            if (null != params.getLastStatus()){
                criteria.andLastStatusEqualTo(params.getLastStatus());
            }
            if (null != params.getStockInventory()){
                if (0 == params.getStockInventory().intValue()){
                    criteria.andStockInventoryGreaterThan(params.getStockInventory());
                }else{
                    criteria.andStockInventoryEqualTo(params.getStockInventory());
                }
            }
        }
    }
}
