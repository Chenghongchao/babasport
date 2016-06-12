package com.lionxxw.babasport.controller.front;

import com.lionxxw.babasport.controller.BaseController;
import com.lionxxw.babasport.core.dto.country.City;
import com.lionxxw.babasport.core.dto.country.Province;
import com.lionxxw.babasport.core.dto.country.Town;
import com.lionxxw.babasport.core.dto.user.Buyer;
import com.lionxxw.babasport.core.query.country.CityQuery;
import com.lionxxw.babasport.core.query.country.TownQuery;
import com.lionxxw.babasport.core.service.country.CityService;
import com.lionxxw.babasport.core.service.country.ProvinceService;
import com.lionxxw.babasport.core.service.country.TownService;
import com.lionxxw.babasport.core.service.user.BuyerService;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.web.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    private SessionProvider sessionProvider;
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

    //个人资料
    @RequestMapping(value = "profile.shtml")
    public String profile(HttpServletRequest request,ModelMap model){
        //加载用户
        Buyer buyer = (Buyer) sessionProvider.getAttribute(request, DataStatus.SESSION_USER);
        Buyer b = buyerService.getBuyerByKey(buyer.getUsername());
        model.addAttribute("buyer", b);
        //省
        List<Province> provinces = provinceService.getProvinceList(null);
        model.addAttribute("provinces", provinces);
        //市
        CityQuery cityQuery = new CityQuery();
        cityQuery.setProvince(b.getProvince());
        List<City> citys = cityService.getCityList(cityQuery);
        model.addAttribute("citys", citys);
        //县
        TownQuery townQuery = new TownQuery();
        townQuery.setCity(b.getCity());
        List<Town> towns = townService.getTownList(townQuery);
        model.addAttribute("towns", towns);

        return "buyer/profile";
    }
}
