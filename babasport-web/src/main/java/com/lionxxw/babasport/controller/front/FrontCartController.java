package com.lionxxw.babasport.controller.front;

import com.lionxxw.babasport.cart.dto.BuyCart;
import com.lionxxw.babasport.cart.dto.BuyItem;
import com.lionxxw.babasport.controller.BaseController;
import com.lionxxw.babasport.product.dto.SkuDto;
import com.lionxxw.babasport.product.service.SkuService;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.utils.JsonUtils;
import com.lionxxw.common.utils.ObjectUtils;
import com.lionxxw.common.utils.ResponseUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Properties;

/**
 * <p>Description: 购物车 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/20 下午5:11
 */
@Controller
@RequestMapping(value = "shopping")
public class FrontCartController extends BaseCartController {

    @Autowired
    private SkuService skuService;

    @RequestMapping(value = "buyCart.shtml")
    public String buyCart(Integer skuId, Integer amount, Integer buyLimit, Integer productId, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception{
        BuyCart buyCart = getBuyCart(request);
        //没有则创建购物车
        if (null == buyCart){
            buyCart = new BuyCart();
        }
        // 第一步:Sku
        if (null != skuId){
            SkuDto sku = new SkuDto();
            sku.setId(skuId);
            // 限制
            if (ObjectUtils.notNull(buyLimit))sku.setStockUpperLimit(buyLimit);
            // 创建购物项
            BuyItem buyItem = new BuyItem();
            buyItem.setSku(sku);
            buyItem.setAmount(amount);
            buyCart.addItem(buyItem); // 添加购物项
            // 最后一款商品的id
            if (ObjectUtils.notNull(productId))buyCart.setProductId(productId);
            updateCookie(response, buyCart, COOKIE_TIME);
        }
        // 装购物车装满
        List<BuyItem> items = buyCart.getItems();
        Properties colorP = colorProperties();
        Properties sizeP = sizeProperties();
        for (BuyItem item : items){
            SkuDto s = skuService.getById(item.getSku().getId());
            s.setColorName(colorP.getProperty(s.getColorId()+""));
            s.setSizeName(sizeP.getProperty(s.getSizeId()+""));
            item.setSku(s);
        }

        modelMap.addAttribute("buyCart", buyCart);
        return "product/cart";
    }

    // 清空购物车
    @RequestMapping(value = "clearCart.shtml")
    public String clearCart(HttpServletResponse response) throws Exception{
        clearCookie(response);
        return "redirect:/shopping/buyCart.shtml";
    }

    // 删除某一个购物项
    @RequestMapping(value = "delItem.shtml")
    public String delProduct(HttpServletRequest request, Integer skuId, HttpServletResponse response)throws Exception{
        BuyCart buyCart = getBuyCart(request);
        if (null != buyCart){
            SkuDto sku = new SkuDto();
            sku.setId(skuId);
            BuyItem buyItem = new BuyItem();
            buyItem.setSku(sku);
            buyCart.deleteItem(buyItem);
            updateCookie(response, buyCart, COOKIE_TIME);
        }

        return "redirect:/shopping/buyCart.shtml";
    }

    // 获取购物车信息
    @RequestMapping(value = "getCart.shtml")
    public void getCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BuyCart buyCart = getBuyCart(request);
        if (null == buyCart){
            ResponseUtils.renderJson(response, "");
            return;
        }
        // 装购物车装满
        List<BuyItem> items = buyCart.getItems();
        Properties colorP = colorProperties();
        Properties sizeP = sizeProperties();
        for (BuyItem item : items){
            SkuDto s = skuService.getById(item.getSku().getId());
            s.setColorName(colorP.getProperty(s.getColorId()+""));
            s.setSizeName(sizeP.getProperty(s.getSizeId()+""));
            item.setSku(s);
        }
        ResponseUtils.renderJson(response, JsonUtils.toJson(buyCart));
    }

    // ajax 删除某一项
    @RequestMapping(value = "deleteItem.shtml")
    public void deleteItem(HttpServletRequest request, Integer skuId, HttpServletResponse response)throws Exception{
        BuyCart buyCart = getBuyCart(request);
        if (null != buyCart){
            SkuDto sku = new SkuDto();
            sku.setId(skuId);
            BuyItem buyItem = new BuyItem();
            buyItem.setSku(sku);
            buyCart.deleteItem(buyItem);
            updateCookie(response, buyCart, COOKIE_TIME);
        }
        JSONObject json = new JSONObject();
        json.put("message", "删除成功");
        ResponseUtils.renderJson(response, json.toString());
    }
}