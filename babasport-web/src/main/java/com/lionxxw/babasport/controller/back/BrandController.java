package com.lionxxw.babasport.controller.back;

import com.lionxxw.babasport.core.dto.BrandDto;
import com.lionxxw.babasport.core.service.BrandService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 后台品牌管理
 *
 */
@Controller
public class BrandController extends BaseBackController{
	
	@Autowired
	private BrandService brandService;

	//品牌列表
	@RequestMapping(value = "/brand/list.do")
	public ModelAndView list(BrandDto params, PageQuery query)throws Exception{
		ModelAndView mv = new ModelAndView();
		PageResult<BrandDto> brands = brandService.queryByPage(params, query);
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
		model.addAttribute("params",params);
		return "redirect:/back/brand/list.do";
	}
}
