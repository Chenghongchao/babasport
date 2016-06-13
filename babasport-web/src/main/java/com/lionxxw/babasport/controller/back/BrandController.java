package com.lionxxw.babasport.controller.back;

import com.lionxxw.babasport.product.dto.BrandDto;
import com.lionxxw.babasport.product.service.BrandService;
import com.lionxxw.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>Description: 后台品牌管理 </p>
 *
 * @author wangxiang
 * @date 16/5/31 下午4:36
 * @version 1.0
 */
@Controller
public class BrandController extends BaseBackController{
	
	@Autowired
	private BrandService brandService;

	//品牌列表
	@RequestMapping(value = "/brand/list.do")
	public ModelAndView list(BrandDto params)throws Exception{
		ModelAndView mv = new ModelAndView();
		if (StringUtils.notTrimEmpty(params.getName())){
			params.setName(URLDecoder.decode(params.getName(), "UTF-8"));
		}
		List<BrandDto> brands = brandService.queryByParam(params);
		mv.addObject("brands", brands);
		mv.addObject("params", params);
		mv.setViewName("brand/list");
		return mv;
	}

	@RequestMapping(value = "/brand/toAdd.do")
	public ModelAndView toAdd(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("brand/add");
		return mv;
	}

	@RequestMapping(value = "/brand/add.do")
	public String add(BrandDto brand) throws Exception{
		brandService.save(brand);
		return "redirect:/back/brand/list.do";
	}

	@RequestMapping(value = "/brand/toEdit.do")
	public ModelAndView toEdit(Integer id) throws Exception {
		ModelAndView mv = new ModelAndView();
		BrandDto brand = brandService.getById(id);
		mv.addObject("brand", brand);
		mv.setViewName("brand/edit");
		return mv;
	}

	@RequestMapping(value = "/brand/edit.do")
	public String edit(BrandDto brand) throws Exception{
		brandService.update(brand);
		return "redirect:/back/brand/list.do";
	}

	@RequestMapping(value = "/brand/delByIds.do")
	public String delByIds(Integer[] ids,BrandDto params, ModelMap model) throws Exception {
		for (Integer id : ids){
			brandService.delById(id);
		}
		if (StringUtils.notTrimEmpty(params.getName())){
			model.addAttribute("name", URLEncoder.encode(params.getName(), "UTF-8"));
		}
		if (null != params.getIsDisplay()){
			model.addAttribute("isDisplay",params.getIsDisplay());
		}
		return "redirect:/back/brand/list.do";
	}
}
