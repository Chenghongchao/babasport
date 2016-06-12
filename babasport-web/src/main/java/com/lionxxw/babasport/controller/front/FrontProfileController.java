package com.lionxxw.babasport.controller.front;

import com.lionxxw.babasport.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Description: 用户个人中心 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/12 上午9:46
 */
@Controller
@RequestMapping(value = "buyer")
public class FrontProfileController extends BaseController{

    //跳转个人中心
    @RequestMapping(value = "index.shtml")
    public String index(){
        //加载订单
        return "buyer/index";
    }

}
