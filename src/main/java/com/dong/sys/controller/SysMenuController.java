package com.dong.sys.controller;

/**
 * @Author:dm
 * @Date:2020/9/13 10:49
 * @Description:
 */

import com.dong.sys.constast.SysConstast;
import com.dong.sys.domain.SysMenu;
import com.dong.sys.domain.SysUser;
import com.dong.sys.service.SysMenuService;
import com.dong.sys.vo.SysMenuVo;
import com.dong.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.jsp.tagext.TryCatchFinally;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理控制器
 *
 * @author LJH
 *
 */
@RestController
@RequestMapping("menu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    @RequestMapping("loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(SysMenuVo menuVo){
        //得到当前登陆的用户对象
        SysUser user=(SysUser) WebUtils.getHttpSession().getAttribute("user");
        List<SysMenu> list=null;
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        if(user.getType()==SysConstast.USER_TYPE_SUPER) {
            list=this.menuService.queryAllMenuForList(menuVo);
        }else {
            list=this.menuService.queryMenuByUserId(menuVo, user.getUserid());
        }
        List<TreeNode> nodes= new ArrayList<>();
        //把list里面的数据放到nodes
        for (SysMenu menu : list) {
            Integer id=menu.getId();
            Integer pid=menu.getPid();
            String title=menu.getTitle();
            String icon=menu.getIcon();
            String href=menu.getHref();
            Boolean spread=menu.getSpread()==SysConstast.SPREAD_TRUE?true:false;
            String target=menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return TreeNodeBuilder.builder(nodes, 1);
    }

    /**
     * 加载菜单管理左边的菜单树
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(SysMenuVo menuVo){
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        SysUser sysUser = (SysUser) WebUtils.getHttpSession().getAttribute("user");

        List<SysMenu> list=this.menuService.queryMenuByUserId(menuVo,sysUser.getUserid());
        List<TreeNode> nodes= new ArrayList<>();
        //把list里面的数据放到nodes
        for (SysMenu menu : list) {
            Integer id=menu.getId();
            Integer pid=menu.getPid();
            String title=menu.getTitle();
            String icon=menu.getIcon();
            String href=menu.getHref();
            Boolean spread=menu.getSpread()==SysConstast.SPREAD_TRUE?true:false;
            String target=menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));
        }
        return new DataGridView(nodes);
    }

    /**
     * 加载菜单列表返回DataGridView
     */
    @RequestMapping("loadAllMenu")
        public DataGridView loadAllMenu(SysMenuVo menuVo) {
        System.out.println("page:"+menuVo.getPage());
        System.out.println("limit:"+menuVo.getLimit());
            return this.menuService.queryAllMenu(menuVo);
    }

    /**
     * 添加菜单
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(SysMenuVo sysMenuVo){
        try{
            this.menuService.addMenu(sysMenuVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     */
    @RequestMapping("updateMenu")
    public  ResultObj updateMenu(SysMenuVo sysMenuVo){
        try {
            this.menuService.updateMenu(sysMenuVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 根据id判断当前菜单有没有子节点
     * 有返回code>=0
     * 没有 返回code<0
     */
    @RequestMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(SysMenuVo menuVo){
        //根据pid查询菜单数量
        Integer count=this.menuService.queryMenuCountsByPid(menuVo.getId());
        if(count>0) {
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 删除菜单
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(SysMenuVo menuVo) {
        try {
            this.menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


}

