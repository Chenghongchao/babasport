package com.lionxxw.babasport.controller.front;

import com.lionxxw.babasport.controller.BaseController;
import com.lionxxw.babasport.country.dto.ProvinceDto;
import com.lionxxw.babasport.country.service.CityService;
import com.lionxxw.babasport.country.service.ProvinceService;
import com.lionxxw.babasport.country.service.TownService;
import com.lionxxw.babasport.user.dto.BuyerDto;
import com.lionxxw.babasport.user.service.BuyerService;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.utils.ResponseUtils;
import com.lionxxw.common.utils.StringUtils;
import com.lionxxw.common.web.SessionProvider;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

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

    @Autowired
    private BuyerService buyerService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TownService townService;

    //跳转个人中心
    @RequestMapping(value = "index.shtml")
    public String index(){
        //加载订单
        return "buyer/index";
    }

    // 个人资料
    @RequestMapping(value = "profile.shtml")
    public ModelAndView profile(HttpServletRequest request) throws Exception{
        BuyerDto buyer = (BuyerDto) sessionProvider.getAttribute(request, DataStatus.SESSION_USER);
        buyer = buyerService.getById(buyer.getUserName());

        ModelAndView mv = new ModelAndView();
        mv.addObject("buyer", buyer);
        mv.addObject("provinces", provinceService.queryByParam(null));
        if (StringUtils.notTrimEmpty(buyer.getProvince())){
            mv.addObject("cities", cityService.queryByProvince(buyer.getProvince()));
            mv.addObject("town", townService.queryByCity(buyer.getCity()));
        }
        mv.setViewName("buyer/profile");
        return mv;
    }

    // 更新个人资料
    @RequestMapping(value = "profile_update.shtml")
    public void update(BuyerDto params, HttpServletRequest request, HttpServletResponse response) throws Exception{
        BuyerDto buyer = (BuyerDto) sessionProvider.getAttribute(request, DataStatus.SESSION_USER);
        buyer.setProvince(params.getProvince());
        buyer.setCity(params.getCity());
        buyer.setTown(params.getTown());
        buyer.setAddr(params.getAddr());
        buyer.setGender(params.getGender());
        buyer.setRealName(params.getRealName());
        buyerService.update(buyer);
        sessionProvider.setAttribute(request, DataStatus.SESSION_USER, buyer);
        JSONObject jo = new JSONObject();
        jo.put("message", "保存成功!");
        ResponseUtils.renderJson(response, jo.toString());
    }
}
