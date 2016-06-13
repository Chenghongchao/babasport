package com.lionxxw.babasport.user.dao;

import com.lionxxw.babasport.user.dto.BuyerDto;
import com.lionxxw.babasport.user.entity.Buyer;
import com.lionxxw.babasport.user.entity.BuyerExample;
import com.lionxxw.babasport.user.mapper.BuyerMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 购买者dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:35
 */
@Repository
public class BuyerDao extends MyBatisBaseDao<Buyer> {

    @Autowired
    @Getter
    private BuyerMapper mapper;

    public List<Buyer> queryByParam(BuyerDto obj, PageQuery query) throws Exception{
        BuyerExample example = new BuyerExample();
        BuyerExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("is_display desc,id asc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("is_display desc,id asc");
        }
        List<Buyer> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(BuyerDto obj) throws Exception{
        BuyerExample example = new BuyerExample();
        BuyerExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(BuyerDto params, BuyerExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getUserName())){
                criteria.andUserNameEqualTo(params.getUserName());
            }
            if (ObjectUtils.notNull(params.getRealName())){
                criteria.andRealNameLike("%" + params.getRealName() + "%");
            }
            if (null != params.getProvince()){
                criteria.andProvinceEqualTo(params.getProvince());
            }
            if (null != params.getCity()){
                criteria.andCityEqualTo(params.getCity());
            }
            if (null != params.getTown()){
                criteria.andTownEqualTo(params.getTown());
            }
            if (null != params.getIsDel()){
                criteria.andIsDelEqualTo(params.getIsDel());
            }else{
                criteria.andIsDelEqualTo(false);
            }
        }else{
            criteria.andIsDelEqualTo(false);
        }
    }
}
