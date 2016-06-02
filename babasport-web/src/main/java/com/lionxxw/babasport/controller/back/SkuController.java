package com.lionxxw.babasport.controller.back;

import com.lionxxw.babasport.core.dto.SkuDto;
import com.lionxxw.babasport.core.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>Description: 商品库存管理 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/2 下午4:34
 */
@Controller
public class SkuController extends BaseBackController {
    @Autowired
    private SkuService skuService;

    // 商品库存列表
    @RequestMapping(value = "/sku/list.do")
    public ModelAndView list(Integer productId, String no) throws Exception{
        ModelAndView mv = new ModelAndView();
        SkuDto params = new SkuDto();
        params.setProductId(productId);
        List<SkuDto> skus = skuService.queryByParam(params);
        mv.addObject("skus", skus);
        mv.addObject("productNo", no);
        mv.setViewName("sku/list");
        return mv;
    }
}
