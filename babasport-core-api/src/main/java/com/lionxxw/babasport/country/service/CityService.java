package com.lionxxw.babasport.country.service;

import com.lionxxw.babasport.country.dto.CityDto;
import com.lionxxw.common.base.BaseService;

import java.util.List;

/**
 * <p>Description: 市接口 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:49
 */
public interface CityService extends BaseService<CityDto>{
    List<CityDto> queryByProvince(String province) throws Exception;
}