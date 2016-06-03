package com.lionxxw.babasport.controller.back;

import com.lionxxw.babasport.service.GeneratePropertiesService;
import com.lionxxw.babasport.trigger.ParameterTrigger;
import com.lionxxw.common.utils.PropertiesUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Properties;

/**
 * <p>Description: 后台controller 基类 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/5/22 下午5:41
 */
@RequestMapping(value = "back")
public class BaseBackController {

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
        Properties properties = PropertiesUtils.returnProperties(ParameterTrigger._PATH+"/"+name+ GeneratePropertiesService.suffix);
        return properties;
    }
}