package com.dong.sys.controller;

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

    }

}
