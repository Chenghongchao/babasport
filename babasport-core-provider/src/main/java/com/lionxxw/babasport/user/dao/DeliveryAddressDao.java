package com.lionxxw.babasport.user.dao;

import com.lionxxw.babasport.user.dto.DeliveryAddressDto;
import com.lionxxw.babasport.user.entity.DeliveryAddress;
import com.lionxxw.babasport.user.entity.DeliveryAddressExample;
import com.lionxxw.babasport.user.mapper.DeliveryAddressMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.utils.ObjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description: 用户收货地址dao层实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:35
 */
@Repository
public class DeliveryAddressDao extends MyBatisBaseDao<DeliveryAddress> {

    @Autowired
    @Getter
    private DeliveryAddressMapper mapper;

    public List<DeliveryAddress> queryByParam(DeliveryAddressDto obj, PageQuery query) throws Exception{
        DeliveryAddressExample example = new DeliveryAddressExample();
        DeliveryAddressExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("is_default desc,add_time desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("is_default desc,add_time desc");
        }
        List<DeliveryAddress> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(DeliveryAddressDto obj) throws Exception{
        DeliveryAddressExample example = new DeliveryAddressExample();
        DeliveryAddressExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(DeliveryAddressDto params, DeliveryAddressExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getBuyer())){
                criteria.andBuyerEqualTo(params.getBuyer());
            }
            if (ObjectUtils.notNull(params.getIsDefault())){
                criteria.andIsDefaultEqualTo(params.getIsDefault());
            }
        }
    }

    /**
     * 重置用户下的收货地址为非默认的
     * @param buyer
     * @throws Exception
     */
    public void resetDefault(String buyer) throws Exception{
        DeliveryAddress record = new DeliveryAddress();
        record.setIsDefault(false);
        DeliveryAddressExample example = new DeliveryAddressExample();
        DeliveryAddressExample.Criteria criteria = example.createCriteria();
        criteria.andBuyerEqualTo(buyer);
        mapper.updateByExampleSelective(record, example);
    }
}
