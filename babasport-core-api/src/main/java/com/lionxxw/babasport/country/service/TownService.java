package com.lionxxw.babasport.country.service;

import com.lionxxw.babasport.country.dto.TownDto;
import com.lionxxw.common.base.BaseService;

import java.util.List;

/**
 * <p>Description: 县/区接口 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:49
 */
public interface TownService extends BaseService<TownDto>{
    List<TownDto> queryByCity(String city) throws  Exception;
}