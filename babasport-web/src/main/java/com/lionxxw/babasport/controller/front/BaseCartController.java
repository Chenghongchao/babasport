package com.lionxxw.babasport.controller.front;

import com.lionxxw.babasport.cart.dto.BuyCart;
import com.lionxxw.babasport.controller.BaseController;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.utils.JsonUtils;
import com.lionxxw.common.utils.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: 购物车controller层基类 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/27 下午2:59
 */
public class BaseCartController extends BaseController{

    public static final int COOKIE_TIME = 60 * 60 * 24; // cookie的保存时间

    /**
     * <p>Description: 从cookie中获取购物车 </p>
     *
     * @param request
     * @return BuyCart
     * @author wangxiang
     * @date 16/6/20 下午9:03
     * @version 1.0
     */
    public BuyCart getBuyCart(HttpServletRequest request) throws Exception {
        // 购物车
        BuyCart buyCart = null;
        // 判断cookie是否有购物车,
        Cookie[] cookies = request.getCookies();
        if (ObjectUtils.notEmpty(cookies)){
            for (Cookie cookie : cookies){
                // 有则使用此购物车
                if (DataStatus.BUYCART_COOKIE.equals(cookie.getName())){
                    buyCart = JsonUtils.toObject(cookie.getValue(), BuyCart.class);
                    break;
                }
            }
        }
        return buyCart;
    }

    /**
     * <p>Description: 更新cookie中的值 </p>
     *
     * @param response
     * @param obj 对象
     * @param expiry 默认:-1 关闭浏览器消失;消灭:0 立即消失;单位秒
     * @return
     * @author wangxiang
     * @date 16/6/20 下午9:44
     * @version 1.0
     */
    public void updateCookie(HttpServletResponse response, Object obj, int expiry) throws Exception{
        // 持久化 对象转json存放cookie中
        Cookie cookie = new Cookie(DataStatus.BUYCART_COOKIE, JsonUtils.toJson(obj));
        // 关闭浏览器,也要保存cookie
        cookie.setMaxAge(expiry);// 默认:-1 关闭浏览器消失;消灭:0 立即消失;单位秒
        // 路径 /shopping/buyCart.shtml 默认 /shopping
        cookie.setPath("/");
        // 发送
        response.addCookie(cookie);
    }

    /**
     * 清空cookie
     * @param response
     */
    public void clearCookie(HttpServletResponse response) throws Exception{
        // 持久化 对象转json存放cookie中
        updateCookie(response, null, 0);
    }
}
