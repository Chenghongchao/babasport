package com.lionxxw.babasport.controller.front;

import com.lionxxw.babasport.controller.BaseController;
import com.lionxxw.babasport.core.dto.*;
import com.lionxxw.babasport.core.service.ProductImageService;
import com.lionxxw.babasport.core.service.ProductService;
import com.lionxxw.babasport.core.service.SkuService;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <p>Description: 前台登录管理 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/3 下午11:56
 */
@Controller
public class FrontLoginController extends BaseController{

    // 登录页面
    @RequestMapping(value = "login.shtml", method = RequestMethod.GET)
    public String login() throws Exception{
        return "buyer/login";
    }

    @RequestMapping(value = "login.shtml", method = RequestMethod.POST)
    public String login(String returnUrl, String username, String password, String captcha) throws Exception{

        return "buyer/login";
    }
}
