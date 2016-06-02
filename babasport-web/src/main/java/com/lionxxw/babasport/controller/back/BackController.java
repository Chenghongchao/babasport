package com.lionxxw.babasport.controller.back;

import com.lionxxw.babasport.core.dto.ColorDto;
import com.lionxxw.babasport.core.dto.MaterialDto;
import com.lionxxw.babasport.core.dto.ProductTypeDto;
import com.lionxxw.babasport.core.dto.SizeDto;
import com.lionxxw.babasport.core.service.ColorService;
import com.lionxxw.babasport.core.service.MaterialService;
import com.lionxxw.babasport.core.service.ProductTypeService;
import com.lionxxw.babasport.core.service.SizeService;
import com.lionxxw.common.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 后台管理
 * 测试
 * @author lx
 *
 */
@Controller
public class BackController extends BaseBackController{

	@Autowired
	private ColorService colorService;
	@Autowired
	private SizeService sizeService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private ProductTypeService typeService;
	
	//每一个Springmvc
	@RequestMapping(value = "/test/springmvc.do")
	public String test(String name,Date birthday){
		System.out.println();
		return "";
	}

	@RequestMapping(value = "/test/aop.do")
	public @ResponseBody Response<String> aop(String service, String methodName, Integer id) throws Exception{
		Response<String> response = new Response<String>();
		if (service.equals("color")){
			if (methodName.equals("save")){
				ColorDto colorDto = new ColorDto();
				colorDto.setName("测试" + System.currentTimeMillis());
				colorService.save(colorDto);
			}else if(methodName.equals("update")){
				ColorDto colorDto = new ColorDto();
				colorDto.setId(1);
				colorDto.setName("测试" + System.currentTimeMillis());
				colorService.update(colorDto);
			}else if(methodName.equals("del")){
				colorService.delById(id);
			}
		}else if (service.equals("size")){
			if (methodName.equals("save")){
				SizeDto colorDto = new SizeDto();
				colorDto.setName("测试" + System.currentTimeMillis());
				sizeService.save(colorDto);
			}else if(methodName.equals("update")){
				SizeDto colorDto = new SizeDto();
				colorDto.setId(1);
				colorDto.setName("测试" + System.currentTimeMillis());
				sizeService.update(colorDto);
			}else if(methodName.equals("del")){
				sizeService.delById(id);
			}
		}else if (service.equals("material")){
			if (methodName.equals("save")){
				MaterialDto colorDto = new MaterialDto();
				colorDto.setName("测试" + System.currentTimeMillis());
				materialService.save(colorDto);
			}else if(methodName.equals("update")){
				MaterialDto colorDto = new MaterialDto();
				colorDto.setId(1);
				colorDto.setName("测试" + System.currentTimeMillis());
				materialService.update(colorDto);
			}else if(methodName.equals("del")){
				materialService.delById(id);
			}
		}else if (service.equals("type")){
			if (methodName.equals("save")){
				ProductTypeDto colorDto = new ProductTypeDto();
				colorDto.setName("测试" + System.currentTimeMillis());
				typeService.save(colorDto);
			}else if(methodName.equals("update")){
				ProductTypeDto colorDto = new ProductTypeDto();
				colorDto.setId(1);
				colorDto.setName("测试" + System.currentTimeMillis());
				typeService.update(colorDto);
			}else if(methodName.equals("del")){
				typeService.delById(id);
			}
		}

		return response;
	}
	
	//跳转入口页面
	@RequestMapping(value = "/index.do")
	public String index(){
		return "index";
	}
	//跳转头页面
	@RequestMapping(value = "/top.do")
	public String top(){
		return "top";
	}
	//跳转身体页面
	@RequestMapping(value = "/main.do")
	public String main(){
		return "main";
	}
	//跳转左页面
	@RequestMapping(value = "/left.do")
	public String left(){
		return "left";
	}
	//跳转右页面
	@RequestMapping(value = "/right.do")
	public String right(){
		return "right";
	}
	

}
