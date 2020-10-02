package com.dong.sys.controller;

/**
 * @Author:dm
 * @Date:2020/9/13 14:58
 * @Description:
 */

import com.dong.sys.service.SysRoleService;
import com.dong.sys.vo.SysRoleVo;
import com.dong.utils.DataGridView;
import com.dong.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理控制器
 *
 * @author LJH
 *
 */
@RestController
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    /**
     * 加载角色列表返回DataGridView
     */
    @RequestMapping("loadAllRole")
    public DataGridView loadAllRole(SysRoleVo roleVo) {
        return this.sysRoleService.queryAllRole(roleVo);
    }


    /**
     * 添加角色
     */
    @RequestMapping("addRole")
    public ResultObj addRole(SysRoleVo roleVo) {
        try {
            this.sysRoleService.addRole(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改角色
     */
    @RequestMapping("updateRole")
    public ResultObj updateRole(SysRoleVo roleVo) {
        try {
            this.sysRoleService.updateRole(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除角色
     */
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(SysRoleVo roleVo) {
        try {
            this.sysRoleService.deleteRole(roleVo.getRoleid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除角色
     */
    @RequestMapping("deleteBatchRole")
    public ResultObj deleteBatchRole(SysRoleVo roleVo) {
        try {
            this.sysRoleService.deleteBatchRole(roleVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载角色管理分配菜单的JSON
     *
     */
    @RequestMapping("initRoleMenuTreeJson")
    public  DataGridView initRoleMenuTreeJson(Integer roleid){
        return this.sysRoleService.initRoleMenuTreeJson(roleid);
    }

    /**
     * 保存角色和菜单的关系
     */
    @RequestMapping("saveRoleMenu")
    public  ResultObj saveRoleMenu(SysRoleVo sysRoleVo){
        try {
            this.sysRoleService.saveRoleMenu(sysRoleVo);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return  ResultObj.DISPATCH_ERROR;
        }
    }


}


