package com.lionxxw.babasport.core.service;

import com.lionxxw.babasport.core.dto.SkuDto;
import com.lionxxw.common.base.BaseService;

import java.util.List;

/**
 * <p>Description: 商品库存接口 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/21 下午11:49
 */
public interface SkuService extends BaseService<SkuDto> {

    /**
     * 根据商品id 查询库存大于0的
     * @param productId
     * @return
     */
    List<SkuDto> queryInventory(Integer productId) throws Exception;
}