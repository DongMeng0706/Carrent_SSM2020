package com.dong.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.http.server.HttpServerResponse;
import com.dong.sys.constast.SysConstast;
import com.dong.sys.domain.SysUser;
import com.dong.sys.service.SysLogLoginService;
import com.dong.sys.service.SysUserService;
import com.dong.sys.vo.SysLogLoginVo;
import com.dong.sys.vo.SysUserVo;
import com.dong.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @Author:dm
 * @Date:2020/9/13 9:06
 * @Description:
 *
 * 用户登陆控制器
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysLogLoginService sysLogLoginService;

    /**
     * 跳转到登陆界面
     */
    @RequestMapping("toLogin")
    public   String toLogin(){
        return "system/main/login";
    }

    /**
     * 登陆方法
     */
    @RequestMapping("login")
    public  String login(SysUserVo sysUserVo, Model model){
        String code = WebUtils.getHttpSession().getAttribute("code").toString();
        if(sysUserVo.getCode().equals(code)){
            SysUser sysUser = this.sysUserService.login(sysUserVo);
            if(null!=sysUser) {
                //放到session
                WebUtils.getHttpSession().setAttribute("user", sysUser);
                //记录登陆日志 向sys_login_log里面插入数据
                //记录登陆日志 向sys_login_log里面插入数据
                SysLogLoginVo logInfoVo=new SysLogLoginVo();
                logInfoVo.setLogintime(new Date());
                logInfoVo.setLoginname(sysUser.getRealname()+"-"+sysUser.getLoginname());
                logInfoVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());

                sysLogLoginService.addLogInfo(logInfoVo);
                return "system/main/index";
            }else {
                model.addAttribute("error",SysConstast.USER_LOGIN_ERROR_MSG);
                return "system/main/login";
            }
        }else{
            model.addAttribute("error",SysConstast.USER_LOGIN_CODE_ERROR_MSG);
            return "system/main/login";
        }


    }

    /**
     * 3.获取验证码
     */
    @RequestMapping("getCode")
    public  void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //定义图片的长度和宽度
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116,36,4,5);
        //将图片验证码存入到session
        session.setAttribute("code",lineCaptcha.getCode());
        //返回验证码图片字节流到首页
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);
    }

}
