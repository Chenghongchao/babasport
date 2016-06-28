package com.lionxxw.babasport.controller.front;

import com.lionxxw.babasport.cart.dto.BuyCart;
import com.lionxxw.babasport.cart.dto.BuyItem;
import com.lionxxw.babasport.controller.BaseController;
import com.lionxxw.babasport.country.dto.ProvinceDto;
import com.lionxxw.babasport.country.service.CityService;
import com.lionxxw.babasport.country.service.ProvinceService;
import com.lionxxw.babasport.country.service.TownService;
import com.lionxxw.babasport.product.dto.SkuDto;
import com.lionxxw.babasport.product.service.SkuService;
import com.lionxxw.babasport.user.dto.BuyerDto;
import com.lionxxw.babasport.user.dto.DeliveryAddressDto;
import com.lionxxw.babasport.user.service.BuyerService;
import com.lionxxw.babasport.user.service.DeliveryAddressService;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.utils.ObjectUtils;
import com.lionxxw.common.utils.ResponseUtils;
import com.lionxxw.common.utils.StringUtils;
import com.lionxxw.common.web.SessionProvider;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

/**
 * <p>Description: 用户个人中心 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/12 上午9:46
 */
@Controller
@RequestMapping(value = "buyer")
public class FrontProfileController extends BaseCartController{

    @Autowired
    private BuyerService buyerService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TownService townService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private DeliveryAddressService deliveryAddressService;

    //跳转个人中心
    @RequestMapping(value = "index.shtml")
    public String index(){
        //加载订单
        return "buyer/index";
    }

    // 个人资料
    @RequestMapping(value = "profile.shtml")
    public ModelAndView profile(HttpServletRequest request) throws Exception{
        BuyerDto buyer = (BuyerDto) sessionProvider.getAttribute(request, DataStatus.SESSION_USER);
        buyer = buyerService.getById(buyer.getUserName());

        ModelAndView mv = new ModelAndView();
        mv.addObject("buyer", buyer);
        mv.addObject("provinces", provinceService.queryByParam(null));
        if (StringUtils.notTrimEmpty(buyer.getProvince())){
            mv.addObject("cities", cityService.queryByProvince(buyer.getProvince()));
            mv.addObject("towns", townService.queryByCity(buyer.getCity()));
        }
        mv.setViewName("buyer/profile");
        return mv;
    }

    // 更新个人资料
    @RequestMapping(value = "profile_update.shtml")
    public void update(BuyerDto params, HttpServletRequest request, HttpServletResponse response) throws Exception{
        BuyerDto buyer = (BuyerDto) sessionProvider.getAttribute(request, DataStatus.SESSION_USER);
        buyer.setProvince(params.getProvince());
        buyer.setCity(params.getCity());
        buyer.setTown(params.getTown());
        buyer.setAddr(params.getAddr());
        buyer.setGender(params.getGender());
        buyer.setRealName(params.getRealName());
        buyerService.update(buyer);
        sessionProvider.setAttribute(request, DataStatus.SESSION_USER, buyer);
        JSONObject jo = new JSONObject();
        jo.put("message", "保存成功!");
        ResponseUtils.renderJson(response, jo.toString());
    }

    // 用户收货地址
    @RequestMapping(value = "deliver_address.shtml")
    public ModelAndView deliver_address(HttpServletRequest request) throws Exception{
        BuyerDto buyer = (BuyerDto) sessionProvider.getAttribute(request, DataStatus.SESSION_USER);
        ModelAndView mv = new ModelAndView();
        DeliveryAddressDto param = new DeliveryAddressDto();
        param.setBuyer(buyer.getUserName());
        mv.addObject("addrs", deliveryAddressService.queryByParam(param));
        mv.setViewName("buyer/deliver_address");
        return mv;
    }

    /**
     * 购物车结算
     * 1.登录判断
     * 2.判断购物车是否有商品
     * 3.判断购物车中的商品是否还有库存,如果没有库存,删除对应cookie值
     * 4.刷新cookie,判断购物车是否有商品
     * 5.加载收货地址
     * 6.回显购物车商品
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "trueBuy.shtml")
    public ModelAndView trueBuy(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mv = new ModelAndView();
        BuyCart buyCart = getBuyCart(request);
        if (buyCart == null || ObjectUtils.isEmpty(buyCart.getItems())){
            // 没有商品就跳转购物车页面,放用户自己点击去首页购买
            mv.setViewName("redirect:/shopping/buyCart.shtml");
            return mv;
        }
        // 装购物车装满
        List<BuyItem> items = buyCart.getItems();
        Properties colorP = colorProperties();
        Properties sizeP = sizeProperties();
        boolean flag = false; // 是否跳转刷新页面,告诉用户某些商品没有库存了
        for (BuyItem item : items){
            SkuDto s = skuService.getById(item.getSku().getId());
            // 判断购物车中的商品是否还有库存,如果没有库存,删除对应cookie值
            if (s.getStockInventory() >= item.getAmount()){
                s.setColorName(colorP.getProperty(s.getColorId()+""));
                s.setSizeName(sizeP.getProperty(s.getSizeId()+""));
                item.setSku(s);
            }else{
                // 删除库存不足的物品
                buyCart.deleteItem(item);
                flag = true;
            }
        }
        if (flag){
            updateCookie(response, buyCart, COOKIE_TIME);
            mv.setViewName("redirect:/shopping/buyCart.shtml");
            return mv;
        }
        BuyerDto buyer = (BuyerDto) sessionProvider.getAttribute(request, DataStatus.SESSION_USER);
        DeliveryAddressDto param = new DeliveryAddressDto();
        param.setBuyer(buyer.getUserName());
        List<DeliveryAddressDto> deliveryAddressDtos = deliveryAddressService.queryByParam(param);
        mv.addObject("addrs", deliveryAddressDtos); // 添加用户收货地址
        mv.addObject("buyCart", buyCart);
        mv.setViewName("/product/productOrder");
        return mv;
    }
}
