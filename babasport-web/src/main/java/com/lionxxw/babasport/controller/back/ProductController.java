package com.lionxxw.babasport.controller.back;

import com.lionxxw.babasport.core.dto.BrandDto;
import com.lionxxw.babasport.core.dto.ProductDto;
import com.lionxxw.babasport.core.service.BrandService;
import com.lionxxw.babasport.core.service.ProductService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

		List<BrandDto> brands = brandService.queryByParam(null);
		mv.addObject("brands", brands);
		mv.setViewName("product/add");
		return mv;
	}

	// 商品新增
	@RequestMapping(value = "/product/add.do")
	public String add(ProductDto product) throws Exception{
		productService.save(product);
		return "redirect:/back/product/list.do";
	}
}
