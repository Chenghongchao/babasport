package com.lionxxw.babasport.user.impl;

import com.lionxxw.babasport.user.dao.BuyerDao;
import com.lionxxw.babasport.user.dto.BuyerDto;
import com.lionxxw.babasport.user.entity.Buyer;
import com.lionxxw.babasport.user.service.BuyerService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.BeanUtils;
import com.lionxxw.common.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>Description: 购买者接口实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 上午12:03
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerDao buyerDao;

    public BuyerDto save(BuyerDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        Buyer buyer = BeanUtils.createBeanByTarget(obj, Buyer.class);
        buyerDao.insertSelective(buyer);
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Buyer.class, "delById");
        int i = buyerDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(BuyerDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getUserName(), Buyer.class, "update");
        Buyer buyer = BeanUtils.createBeanByTarget(obj, Buyer.class);
        buyerDao.updateByPrimaryKeySelective(buyer);
    }

    public BuyerDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, Buyer.class, "getById");
        Buyer buyer = buyerDao.selectByPrimaryKey(id);
        BuyerDto dto = BeanUtils.createBeanByTarget(buyer, BuyerDto.class);
        return dto;
    }

    public List<BuyerDto> queryByParam(BuyerDto obj) throws Exception {
        List<Buyer> buyers = buyerDao.queryByParam(obj, null);
        if (null != buyers && buyers.size() > 0){
            List<BuyerDto> list = BeanUtils.createBeanListByTarget(buyers, BuyerDto.class);
            return list;
        }
        return null;
    }

    public PageResult<BuyerDto> queryByPage(BuyerDto obj, PageQuery query) throws Exception {
        int total = buyerDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<Buyer> buyers = buyerDao.queryByParam(obj, query);
            List<BuyerDto> list = BeanUtils.createBeanListByTarget(buyers, BuyerDto.class);
            return new PageResult<BuyerDto>(query, list);
        }
        return null;
    }

    public BuyerDto getByUserName(String userName) throws Exception {
        return getById(userName);
    }

    public BuyerDto getById(String userName) throws Exception {
        ExceptionUtils.checkIdIsNull(userName, Buyer.class, "getById");
        Buyer buyer = buyerDao.selectByPrimaryKey(userName);
        if (null != buyer && !buyer.getIsDel()){
            BuyerDto dto = BeanUtils.createBeanByTarget(buyer, BuyerDto.class);
            return dto;
        }
        return null;
    }
}
