package com.lionxxw.babasport.interceptor;

import com.lionxxw.babasport.core.dto.user.Buyer;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.web.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: 前台登录拦截器 </p>
 * 判断 用户是否登陆
 * 全站上下文
 * 全局信息
 * @author wangxiang
 * @version 1.0
 * @time 16/6/12 下午1:37
 */
public class FrontInterceptor implements HandlerInterceptor {
    @Autowired
    private SessionProvider sessionProvider;
    //拦截 包括/buyer  的请求路径
    public static final String INTERCETOR_URL = "/buyer/";
    //返回路径
    public static final String RETURNURL = "returnUrl";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Buyer buyer = (Buyer) sessionProvider.getAttribute(request, DataStatus.SESSION_USER);
        if(null != buyer){
            request.setAttribute("isLogin", true);
        }else{
            request.setAttribute("isLogin", false);
        }
        String requestURI = request.getRequestURI();

        if(requestURI.contains(INTERCETOR_URL)){
            if(null == buyer){
                response.sendRedirect("/login.shtml?" + RETURNURL + "=" + requestURI);
                return false;
            }
        }

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
