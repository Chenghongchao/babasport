package com.lionxxw.babasport.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 模块身体加载
 * @author lx
 *
 */
@Controller
@RequestMapping(value = "back/frame")
public class FrameController {

	//商品身体
	@RequestMapping(value = "/product_main.do")
	public String productMain(){
		
		return "frame/product_main";
	}
	//商品左身体
	@RequestMapping(value = "/product_left.do")
	public String productLeft(){
		
		return "frame/product_left";
	}
	//订单的身体
	@RequestMapping(value = "/order_main.do")
	public String orderMain(){
		
		return "frame/order_main";
	}
	//订单的左
	@RequestMapping(value = "/order_left.do")
	public String orderLeft(){
		
		return "frame/order_left";
	}
}
