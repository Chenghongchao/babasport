package com.lionxxw.babasport.controller.back;

import com.lionxxw.babasport.core.dto.product.SkuDto;
import com.lionxxw.babasport.core.service.SkuService;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.model.Response;
import com.lionxxw.common.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Properties;

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
        assembly(skus);
        mv.addObject("skus", skus);
        mv.addObject("productNo", no);
        mv.setViewName("sku/list");
        return mv;
    }

    /**
     * <p>Description: 商品库存更新接口 </p>
     *
     * @param sku 库存信息
     * @return  Response<String> ajax返回信息
     * @author wangxiang
     * @date 16/6/3 下午3:15
     * @version 1.0
     */
    @RequestMapping(value = "/sku/update.do")
    @ResponseBody
    public Response<String> update(SkuDto sku) throws Exception{
        Response<String> response = new Response<String>();
        try {
            checkSku(sku);
            skuService.update(sku);
            response.setMessage("修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(DataStatus.HTTP_FAILE);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * <p>Description: 后台也进行一次,库存数据校验 </p>
     *
     * @param sku 商品库存数据
     * @author wangxiang
     * @date 16/6/3 下午3:18
     * @version 1.0
     */
    private void checkSku(SkuDto sku) throws Exception{
        if (null == sku){
            throw new RuntimeException("商品库存数据为空!");
        }
        if (ObjectUtils.isNull(sku.getId())){
            throw new RuntimeException("商品库存主键id为空!");
        }
    }

    private void assembly(List<SkuDto> skus) {
        if (null != skus && skus.size() > 0){
            Properties cp = colorProperties();
            Properties sp = sizeProperties();
            for (SkuDto sku : skus){
                sku.setColorName(cp.getProperty(sku.getColorId()+""));
                sku.setSizeName(sp.getProperty(sku.getSizeId() + ""));
            }
        }
    }
}
