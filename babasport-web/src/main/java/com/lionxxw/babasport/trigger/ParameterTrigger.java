package com.lionxxw.babasport.trigger;

import com.lionxxw.babasport.product.dto.ColorDto;
import com.lionxxw.babasport.product.dto.MaterialDto;
import com.lionxxw.babasport.product.dto.ProductTypeDto;
import com.lionxxw.babasport.product.dto.SizeDto;
import com.lionxxw.babasport.product.service.ColorService;
import com.lionxxw.babasport.product.service.MaterialService;
import com.lionxxw.babasport.product.service.ProductTypeService;
import com.lionxxw.babasport.product.service.SizeService;
import com.lionxxw.babasport.service.GeneratePropertiesService;
import com.lionxxw.babasport.service.GeneratePropertiesServiceImpl;
import com.lionxxw.common.utils.reflect.DynamicMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 常用参数配置生成器 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/2 下午7:58
 */
public class ParameterTrigger {
    protected static final Log logger = LogFactory.getLog(ParameterTrigger.class);

    @Autowired
    private ColorService colorService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProductTypeService typeService;
    @Autowired
    private GeneratePropertiesService generatePropertiesService;

    public static final String _COLOR = "color";
    public static final String _SIZE = "size";
    public static final String _MATERIAL = "material";
    public static final String _TYPE = "type";
    public static final String _PATH = "properties";

    /**
     * 初始化配置文件
     */
    private void init() throws Exception{
        logger.info("常用参数配置生成器  ------> init    start....");
        generateColor();
        generateSize();
        generateMaterial();
        generateType();
        logger.info("常用参数配置生成器  ------> init    end....");
    }

    public void generateColor() throws Exception{
        logger.info("常用参数配置生成器 generateColor start....");
        generatePropertiesService.generateToResource(queryColor(), _PATH, _COLOR, "商品颜色配置");
        logger.info("常用参数配置生成器 generateColor end....");
    }
    public void generateSize() throws Exception{
        logger.info("常用参数配置生成器 generateSize start....");
        generatePropertiesService.generateToResource(querySize(), _PATH, _SIZE, "商品尺码配置");
        logger.info("常用参数配置生成器 generateSize end....");
    }
    public void generateMaterial() throws Exception{
        logger.info("常用参数配置生成器 generateMaterial start....");
        generatePropertiesService.generateToResource(queryMaterial(), _PATH, _MATERIAL, "商品材质配置");
        logger.info("常用参数配置生成器 generateMaterial end....");
    }
    public void generateType() throws Exception{
        logger.info("常用参数配置生成器 generateType start....");
        generatePropertiesService.generateToResource(queryType(), _PATH, _TYPE, "商品类型配置");
        logger.info("常用参数配置生成器 generateType end....");
    }

    private List<GeneratePropertiesServiceImpl.PropertiesData> queryColor() throws Exception{
        List<ColorDto> colors = colorService.queryByParam(null);
        return dtoToPertiesData(colors);
    }

    private List<GeneratePropertiesServiceImpl.PropertiesData> querySize() throws Exception{
        List<SizeDto> sizes = sizeService.queryByParam(null);
        return dtoToPertiesData(sizes);
    }

    private List<GeneratePropertiesServiceImpl.PropertiesData> queryMaterial() throws Exception{
        List<MaterialDto> materials = materialService.queryByParam(null);
        return dtoToPertiesData(materials);
    }

    private List<GeneratePropertiesServiceImpl.PropertiesData> queryType() throws Exception{
        List<ProductTypeDto> types = typeService.queryByParam(null);
        return dtoToPertiesData(types);
    }

    private List<GeneratePropertiesServiceImpl.PropertiesData> dtoToPertiesData(List<?> dtos){
        if (null != dtos && dtos.size() > 0){
            List<GeneratePropertiesServiceImpl.PropertiesData> datas = new ArrayList<GeneratePropertiesServiceImpl.PropertiesData>(dtos.size());
            GeneratePropertiesServiceImpl.PropertiesData data;
            for (Object dto : dtos){
                data = new GeneratePropertiesServiceImpl.PropertiesData(DynamicMethod.invokeMethod(dto, "getId")+"", DynamicMethod.invokeMethod(dto, "getName")+"");
                datas.add(data);
            }
            return datas;
        }
        return null;
    }
}
