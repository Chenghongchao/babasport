package com.lionxxw.babasport.controller.front;

import com.lionxxw.babasport.controller.BaseController;
import com.lionxxw.babasport.core.dto.user.BuyerDto;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.encode.EncodeMd5;
import com.lionxxw.common.utils.StringUtils;
import com.lionxxw.common.web.SessionProvider;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: 前台登录管理 </p>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/3 下午11:56
 */
@Controller
public class FrontLoginController extends BaseController{
    @Autowired
    private SessionProvider sessionProvider;
    @Autowired
    private EncodeMd5 encodeMd5;
    @Autowired
    private ImageCaptchaService imageCaptchaService;
//    @Autowired
//    private BuyerService buyerService;

    // 登录页面
    @RequestMapping(value = "login.shtml", method = RequestMethod.GET)
    public String login() throws Exception{
        return "buyer/login";
    }

    /**
     * <p>Description: 用户登录接口 </p>
     * 业务逻辑:
     * 1.验证码是否为空
     * 2.验证码是否正确
     * 3.用户名是否为空
     * 4.密码是否为空
     * 5.用户名是否正确
     * 6.密码是否正确
     * 7.放进session 跳转returnUrl
     *
     * @param returnUrl 用户登录成功跳转页面
     * @param username  用户名
     * @param password  密码
     * @param captcha   验证码
     * @return string
     * @author wangxiang
     * @date 16/6/11 下午10:26
     * @version 1.0
     */
    @RequestMapping(value = "login.shtml", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, String returnUrl, String username, String password, String captcha) throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/buyer/login");
        if (StringUtils.isTrimEmpty(captcha)){
            mv.addObject("error","请填写验证码");
        }else{
            if(imageCaptchaService.validateResponseForID(sessionProvider.getSessionId(request), captcha)){
                if (StringUtils.isTrimEmpty(username)){
                    mv.addObject("error","请输入用户名");
                }else{
                    if (StringUtils.isTrimEmpty(password)){
                        mv.addObject("error","请输入密码");
                    }else{
                        BuyerDto params = new BuyerDto();
                        params.setUsername(username);
                        // TODO 测试
                        BuyerDto buyer = new BuyerDto();
                        buyer.setUsername("admin");
                        buyer.setPassword("305bfce06b8affc456212690586e380b");
                        if (null != buyer){
                            if (encodeMd5.encode(password).equals(buyer.getPassword())){
                                sessionProvider.setAttribute(request, DataStatus.SESSION_USER, buyer);
                                if (StringUtils.isTrimEmpty(returnUrl)){
                                    // 个人中心
                                    mv.setViewName("redirect:/buyer/index.shtml");
                                }else{
                                    mv.setViewName("redirect:"+returnUrl);
                                }
                            }else{
                                mv.addObject("error","用户/密码不正确");
                            }
                        }else{
                            mv.addObject("error","用户不存在");
                        }
                    }
                }
            }else{
                mv.addObject("error","验证码输入有误");
            }
        }

        return mv;
    }

    // 登出
    @RequestMapping(value = "logout.shtml", method = RequestMethod.GET)
    public String logout(String returnUrl ,HttpServletRequest request,HttpServletResponse response) throws Exception{
        sessionProvider.logout(request, response);
        return "redirect:" + returnUrl;
    }
}