package com.lionxxw.babasport.staticpage;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Map;

/**
 * <p>Description: 基于freemarker实现动态页面静态化实现 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/6 下午5:09
 */
public class StaticPageServiceImpl implements StaticPageService,ServletContextAware {

    private ServletContext servletContext;
    private Configuration conf;

    public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
        this.conf = freeMarkerConfigurer.getConfiguration();
    }

    // 静态化方法
    public void productIndex(Map<String, Object> root, Integer id) {
        // 设置模板的目录(改由spring配置文件中配置模板路径)
        /*String dir = "/WEB-INF/ftl/";
        conf.setDirectoryForTemplateLoading(dir);*/
        // 输出流 从内存中写到磁盘上
        Writer out = null;
        try {
            // 读进来  UTF-8 内存中
            Template template = conf.getTemplate("productDetail.html");
            // 获取html的路径
            String path = getPath("/html/product/" + id + ".html");
            File file = new File(path);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            // 处理模板
            template.process(root, out);
        } catch (Exception e) {
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

    //获取路径
    public String getPath(String name){
        return servletContext.getRealPath(name);
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;

    }
}
