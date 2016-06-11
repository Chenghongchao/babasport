package com.lionxxw.common.web;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * <p>Description: session接口 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/11 下午3:23
 */
public interface SessionProvider {

    /**
     * <p>Description: 往session设置值 </p>
     *
     * @param request
     * @param name session key值
     * @param value session value值
     * @author wangxiang
     * @date 16/6/11 下午3:26
     * @version 1.0
     */
    void setAttribute(HttpServletRequest request, String name, Serializable value);

    /**		
     * <p>Description: 从session中取值 </p>
     * 
     * @param request
     * @param name
     * @return Serializable
     * @author wangxiang	
     * @date 16/6/11 下午3:32
     * @version 1.0
     */
    Serializable getAttribute(HttpServletRequest request, String name);
    
    /**		
     * <p>Description: 退出登录 </p>
     * 
     * @param request
     * @author wangxiang
     * @date 16/6/11 下午3:33
     * @version 1.0
     */
    void logout(HttpServletRequest request);
    
    /**		
     * <p>Description: 获取sessionId </p>
     * 
     * @param request
     * @return string sessionId
     * @author wangxiang	
     * @date 16/6/11 下午3:34
     * @version 1.0
     */
    String getSessionId(HttpServletRequest request);
}