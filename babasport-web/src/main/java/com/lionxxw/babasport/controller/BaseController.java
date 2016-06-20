package com.lionxxw.babasport.controller;

import com.lionxxw.babasport.service.GeneratePropertiesService;
import com.lionxxw.babasport.trigger.ParameterTrigger;
import com.lionxxw.common.utils.PropertiesUtils;
import com.lionxxw.common.web.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

/**
 * <p>Description: 基类controller </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/4 上午12:16
 */
public class BaseController {
    public Properties colorProperties(){
        return getProperties(ParameterTrigger._COLOR);
    }

    public Properties sizeProperties(){
        return getProperties(ParameterTrigger._SIZE);
    }

    public Properties materialProperties(){
        return getProperties(ParameterTrigger._MATERIAL);
    }

    public Properties typeProperties(){
        return getProperties(ParameterTrigger._TYPE);
    }

    public Properties getProperties(String name){
        Properties properties = PropertiesUtils.returnProperties(ParameterTrigger._PATH + "/" + name + GeneratePropertiesService.suffix);
        return properties;
    }

    @Autowired
    public SessionProvider sessionProvider;
}
