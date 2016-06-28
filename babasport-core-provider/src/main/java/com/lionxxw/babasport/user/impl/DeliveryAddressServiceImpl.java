package com.lionxxw.babasport.user.impl;

import com.lionxxw.babasport.user.dao.DeliveryAddressDao;
import com.lionxxw.babasport.user.dto.DeliveryAddressDto;
import com.lionxxw.babasport.user.entity.DeliveryAddress;
import com.lionxxw.babasport.user.service.DeliveryAddressService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtils;
import com.lionxxw.common.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 用户收货地址接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/28 上午10:47
 */
@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService{
    @Autowired
    private DeliveryAddressDao deliveryAddressDao;

    public DeliveryAddressDto save(DeliveryAddressDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        DeliveryAddress deliveryAddress = BeanUtils.createBeanByTarget(obj, DeliveryAddress.class);
        deliveryAddressDao.insertSelective(deliveryAddress);
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, DeliveryAddress.class, "delById");
        int i = deliveryAddressDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(DeliveryAddressDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        DeliveryAddress deliveryAddress = BeanUtils.createBeanByTarget(obj, DeliveryAddress.class);
        deliveryAddressDao.updateByPrimaryKeySelective(deliveryAddress);
    }

    public DeliveryAddressDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, DeliveryAddress.class, "getById");
        DeliveryAddress deliveryAddress = deliveryAddressDao.selectByPrimaryKey(id);
        DeliveryAddressDto dto = BeanUtils.createBeanByTarget(deliveryAddress, DeliveryAddressDto.class);
        return dto;
    }

    public List<DeliveryAddressDto> queryByParam(DeliveryAddressDto obj) throws Exception {
        List<DeliveryAddress> deliveryAddress = deliveryAddressDao.queryByParam(obj, null);
        if (null != deliveryAddress && deliveryAddress.size() > 0){
            List<DeliveryAddressDto> list = BeanUtils.createBeanListByTarget(deliveryAddress, DeliveryAddressDto.class);
            return list;
        }
        return null;
    }

    public PageResult<DeliveryAddressDto> queryByPage(DeliveryAddressDto obj, PageQuery query) throws Exception {
        int total = deliveryAddressDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<DeliveryAddress> deliveryAddresses = deliveryAddressDao.queryByParam(obj, query);
            List<DeliveryAddressDto> list = BeanUtils.createBeanListByTarget(deliveryAddresses, DeliveryAddressDto.class);
            return new PageResult<DeliveryAddressDto>(query, list);
        }
        return null;
    }
}
