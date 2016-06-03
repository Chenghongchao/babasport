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

    String suffix = ".properties"; // 统一后缀

    /**
     * <p>Description: 生成方法(生成路径相对于webapp目录) </p>
     *
     * @param datas 数据
     * @param path 生成路径(/properties/fileName),相对于webapp目录
     * @param comment 注释(文件说明)
     * @author wangxiang
     * @date 16/6/2 下午7:15
     * @version 1.0
     */
    void generateToWebapp(List<GeneratePropertiesServiceImpl.PropertiesData> datas, String path, String comment);

    /**
     * <p>Description: 生成方法(生成路径相对于resource目录) </p>
     *
     * @param datas 数据
     * @param path 生成路径(/properties),相对于resource目录
     * @param file 文件名(file)
     * @param comment 注释(文件说明)
     * @author wangxiang
     * @date 16/6/2 下午7:15
     * @version 1.0
     */
    void generateToResource(List<GeneratePropertiesServiceImpl.PropertiesData> datas, String path, String file, String comment);
}
