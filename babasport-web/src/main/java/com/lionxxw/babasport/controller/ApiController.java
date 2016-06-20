package com.lionxxw.babasport.controller;

import com.lionxxw.babasport.country.dto.CityDto;
import com.lionxxw.babasport.country.dto.ProvinceDto;
import com.lionxxw.babasport.country.dto.TownDto;
import com.lionxxw.babasport.country.entity.City;
import com.lionxxw.babasport.country.service.CityService;
import com.lionxxw.babasport.country.service.ProvinceService;
import com.lionxxw.babasport.country.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>Description: 暴露外部接口 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/19 下午8:22
 */
@Controller
@RequestMapping(value = "api")
public class ApiController {
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TownService townService;

    // 查询所有省
    @RequestMapping("queryProvince.do")
    @ResponseBody
    public List<ProvinceDto> queryProvince() throws Exception{
        return provinceService.queryByParam(null);
    }

    @RequestMapping("queryCity.do")
    @ResponseBody
    public List<CityDto> queryCity(String code) throws Exception{
        return cityService.queryByProvince(code);
    }

    @RequestMapping("queryTown.do")
    @ResponseBody
    public List<TownDto> queryTown(String code) throws Exception{
        return townService.queryByCity(code);
    }
}