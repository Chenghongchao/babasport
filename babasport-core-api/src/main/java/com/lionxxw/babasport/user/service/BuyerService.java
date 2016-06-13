package com.lionxxw.babasport.user.service;

import com.lionxxw.babasport.user.dto.BuyerDto;
import com.lionxxw.common.base.BaseService;

/**
 * <p>Description: 购买者接口 </p>
 *
 * @author wangxiang
 * @date 16/6/11 下午10:44
 * @version 1.0
 */
public interface BuyerService extends BaseService<BuyerDto>{

    BuyerDto getByUserName(String userName) throws Exception;

    BuyerDto getById(String userName) throws Exception;
}
