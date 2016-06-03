package com.lionxxw.babasport.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * <p>Description: 生成properties文件触发器 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/2 下午5:07
 */
@Service
public class GeneratePropertiesServiceImpl implements GeneratePropertiesService,ServletContextAware {
    
    private ServletContext servletContext;

    public void generateToWebapp(List<PropertiesData> datas, String path, String comment){
        if (null != datas && datas.size() > 0){
            String allPath = getPath(path+suffix);
            generate(datas, comment, allPath);
        }
    }

    public void generateToResource(List<PropertiesData> datas, String path, String file, String comment){
        if (null != datas && datas.size() > 0){
            StringBuilder allPath = new StringBuilder(getResourcePath(path));
            allPath.append(file + suffix);
            generate(datas, comment, allPath.toString());
        }
    }

    /**
     * <p>Description: properties生成方法 </p>
     *
     * @param datas 数据
     * @param comment 注释
     * @param allPath 生成位置全路径
     * @return
     * @author wangxiang
     * @date 16/6/3 上午10:24
     * @version 1.0
     */
    private void generate(List<PropertiesData> datas, String comment, String allPath) {
        Properties prop = new Properties();
        for (PropertiesData data : datas){
            prop.put(data.getKey(), data.getValue());
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(allPath.toString());
            //为properties添加注释
            prop.store(out, comment);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * <p>Description: 获取全路径(相对于webapp目录) </p>
     * 
     * @param name 文件名
     * @return path 全路径
     * @author wangxiang	
     * @date 16/6/2 下午6:55
     * @version 1.0
     */
    public String getPath(String name){
        return servletContext.getRealPath("/"+name);
    }

    /**
     * <p>Description: 获取resource目录全路径  </p>
     *
     * @param name 路径 相对于resource 如:(/properties)
     * @return
     * @author wangxiang
     * @date 16/6/3 上午10:21
     * @version 1.0
     */
    public String getResourcePath(String name){
        URL resource = GeneratePropertiesServiceImpl.class.getResource("/"+name);
        return resource.getPath();
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 一个内部类,用于封装指定格式
     */
    public static class PropertiesData {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public PropertiesData() {
        }

        public PropertiesData(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
