package com.lionxxw.babasport.service;

import java.util.List;

/**
 * <p>Description: 自动生成properties文件接口 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/2 下午7:13
 */
public interface GeneratePropertiesService {

    /**
     * <p>Description: 生成方法 </p>
     *
     * @param datas 数据
     * @param path 生成路径(/properties/class),相对于webapp目录
     * @param comment 注释(文件说明)
     * @author wangxiang
     * @date 16/6/2 下午7:15
     * @version 1.0
     */
    void generate(List<GeneratePropertiesServiceImpl.PropertiesData> datas, String path, String comment);
}
