package com.lionxxw.babasport.staticpage;

import java.util.Map;

/**
 * <p>Description: 基于 freemarker 动态页面静态化 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/6 下午4:55
 */
public interface StaticPageService {
    void productIndex(Map<String, Object> root, Integer id);
}