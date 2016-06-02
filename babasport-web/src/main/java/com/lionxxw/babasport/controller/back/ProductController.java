package com.lionxxw.babasport.controller.back;

import com.lionxxw.babasport.core.dto.*;
import com.lionxxw.babasport.core.entity.ProductType;
import com.lionxxw.babasport.core.service.*;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.utils.UUIDGenerator;
import com.lionxxw.common.utils.UploadImageUtil;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


/**
 * <p>Description: 后台商品管理 </p>
 *
 * @author wangxiang
 * @date 16/5/31 下午4:35
 * @version 1.0
 */
@Controller
public class ProductController extends BaseBackController{
	
	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private SizeService sizeService;
	@Autowired
	private ProductTypeService typeService;
	@Autowired
	private MaterialService materialService;

	// 商品列表
	@RequestMapping(value = "/product/list.do")
	public ModelAndView list(ProductDto params, PageQuery query) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BrandDto> brands = brandService.queryByParam(null);
		PageResult<ProductDto> products = productService.queryByPage(params, query);

		mv.addObject("brands", brands);
		mv.addObject("products", products);
		mv.addObject("params", params);
		mv.setViewName("product/list");
		return mv;
	}

	// 商品添加跳转页
	@RequestMapping(value = "/product/toAdd.do")
	public ModelAndView toAdd() throws Exception{
		ModelAndView mv = new ModelAndView();
		productParamsInit(mv);
		mv.setViewName("product/add");
		return mv;
	}

	// 商品新增
	@RequestMapping(value = "/product/add.do")
	public String add(ProductDto product, ProductImageDto image) throws Exception{
		product.setImage(image);
		productService.save(product);
		return "redirect:/back/product/list.do";
	}

	/**		
	 * <p>Description: 新增商品时,需要加载的配置 </p>
	 * 
	 * @param mv
	 * @author wangxiang
	 * @date 16/6/2 上午10:56
	 * @version 1.0
	 */
	private void productParamsInit(ModelAndView mv) throws Exception{
		List<BrandDto> brands = brandService.queryByParam(null);
		mv.addObject("brands", brands);
		List<ColorDto> colors = colorService.queryByParam(null);
		mv.addObject("colors", colors);
		List<SizeDto> sizes = sizeService.queryByParam(null);
		mv.addObject("sizes", sizes);
		List<ProductTypeDto> types = typeService.queryByParam(null);
		mv.addObject("types", types);
		List<MaterialDto> materials = materialService.queryByParam(null);
		mv.addObject("materials", materials);
	}
}
