package com.lionxxw.common.constants;

/**
 * <p>Description: 数据状态常量定义 </p>
 * 1-有效 0-无效
 * @author wangxiang
 * @date 16/5/6 上午9:25
 * @version 1.0
 */
public interface DataStatus {

    int ENABLED = 1;   // 数据有效

    int DISABLED = 0;  // 数据无效

    int SEX_MAN = 0;   // 性别-男

    int SEX_WOMAN = 1; // 性别-女

    int HTTP_SUCCESS = 200; // http请求成功

    int HTTP_FAILE = 500; // http请求失败

    String DECODECHARSET = "utf-8";    // 默认字符编码

    String SESSION_EMP = "session_emp"; // 后台员工session key
    String SESSION_USER = "session_user"; // 前台用户session key

    String BUYCART_COOKIE = "babasprot_buyCart_cookie"; // 购物车cookie key

    /**
     * 图片服务器
     */
    String IMAGE_URL = "http://localhost:8088/image-web/";
}
