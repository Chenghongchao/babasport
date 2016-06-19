package com.lionxxw.babasport.controller.front;

import com.lionxxw.babasport.controller.BaseController;
import com.lionxxw.babasport.product.dto.*;
import com.lionxxw.babasport.product.entity.ProductType;
import com.lionxxw.babasport.product.service.*;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * <p>Description: 前台商品页面控制层 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/3 下午11:56
 */
@Controller
@RequestMapping(value = "product")
public class FrontProductController extends BaseController{

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService imageService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private MaterialService materialService;

    //商品列表页面
    // 1.使用oscache页面缓存技术
    // 待实现优化技术.solr
    @RequestMapping(value = "display/list.shtml")
    public ModelAndView list(ProductDto params, PageQuery query) throws Exception{
        query.setPageSize(12);
        ModelAndView mv = new ModelAndView();
        PageResult<ProductDto> pageResult = productService.queryByPage(params, query);
        Map<String,String> filterMap = new LinkedHashMap<String, String>();
        boolean filter = false; // 是否显示过滤条件(默认不显示)
        if (null == params.getBrandId()){
            List<BrandDto> brands = brandService.queryByParam(null);
            mv.addObject("brands", brands);
        }else{
            filter = true;
            filterMap.put("品牌", params.getBrandName());
        }
        if (null == params.getTypeId()){
            List<ProductTypeDto> types = productTypeService.queryByParam(null);
            mv.addObject("types", types);
        }else{
            filter = true;
            filterMap.put("类型", params.getTypeName());
        }
        if (StringUtils.isTrimEmpty(params.getFeature())){
            List<MaterialDto> materials = materialService.queryByParam(null);
            mv.addObject("materials", materials);
        }else{
            filter = true;
            filterMap.put("材质", params.getFeatureName());
        }

        mv.addObject("products", pageResult);
        mv.addObject("params", params);
        mv.addObject("filter", filter);
        mv.addObject("filterMap", filterMap);
        mv.setViewName("product/product");
        return mv;
    }

    //商品详情页面
    @RequestMapping(value = "detail.shtml")
    public ModelAndView detail(Integer id) throws Exception{
        ModelAndView mv = new ModelAndView();
        //业务数据
        //1商品图片加载
        ProductDto product = productService.getById(id);
        mv.addObject("images",getProductImageDtos(product));
        //2:加载sku
        //3:此商品  库存大于0的
        List<SkuDto> skus = skuService.queryInventory(id);
        if(null != skus && skus.size() > 0){
            //4:不一样的颜色集合(去重)
            mv.addObject("colors", getColors(skus));
            //5:不一样的尺寸集合(去重)
            mv.addObject("sizes", getSizes(skus));
            mv.addObject("skus", skus);
        }
        mv.addObject("product", product);
        mv.setViewName("product/productDetail");
        return mv;
    }

    private List<ProductImageDto> getProductImageDtos(ProductDto product) throws Exception {
        ProductImageDto params = new ProductImageDto();
        params.setProductId(product.getId());
        List<ProductImageDto> productImageDtos = imageService.queryByParam(params);
        return productImageDtos;
    }

    private List<ColorDto> getColors(List<SkuDto> skus) {
        List<Integer> colorIds = new ArrayList<Integer>();
        List<ColorDto> colors = new ArrayList<ColorDto>();
        ColorDto color;
        Properties cp = colorProperties();
        for(SkuDto sku : skus){
            if(!colorIds.contains(sku.getColorId())){
                colorIds.add(sku.getColorId());
                color = new ColorDto();
                color.setId(sku.getColorId());
                color.setName(cp.getProperty(sku.getColorId() + ""));
                colors.add(color);
            }
        }
        return colors;
    }

    private List<SizeDto> getSizes(List<SkuDto> skus) {
        List<Integer> sizeIds = new ArrayList<Integer>();
        List<SizeDto> sizes = new ArrayList<SizeDto>();
        SizeDto size;
        Properties sp = sizeProperties();
        for(SkuDto sku : skus){
            if(!sizeIds.contains(sku.getSizeId())){
                sizeIds.add(sku.getSizeId());
                size = new SizeDto();
                size.setId(sku.getSizeId());
                size.setName(sp.getProperty(sku.getSizeId() + ""));
                sizes.add(size);
            }
        }
        return sizes;
    }
}