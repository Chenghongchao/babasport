package com.lionxxw.common.web;

import com.lionxxw.common.constants.DataStatus;
import javafx.scene.shape.VLineTo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * <p>Description: 本地session </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/11 下午3:23
 */
public class HttpSessionProvider implements SessionProvider {

    public void setAttribute(HttpServletRequest request, String name, Serializable value) {
        HttpSession session = request.getSession();
        session.setAttribute(name, value);
    }

    public Serializable getAttribute(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(false);
        if (null != session){
            return (Serializable)session.getAttribute(name);
        }
        return null;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (null != session){
            session.invalidate();
        }
        // 清除cookie jsessionId
        Cookie c  = new Cookie(DataStatus.SESSION_USER,null);
        c.setMaxAge(0);
        response.addCookie(c);

    }

    public String getSessionId(HttpServletRequest request) {
        return request.getSession().getId();
    }
}
