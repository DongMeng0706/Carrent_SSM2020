package com.dong.sys.controller;

/**
 * @Author:dm
 * @Date:2020/9/14 10:51
 * @Description:
 */

import com.dong.sys.service.SysUserService;
import com.dong.sys.vo.SysUserVo;
import com.dong.utils.DataGridView;
import com.dong.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理控制器
 *
 * @author LJH
 *
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    /**
     * 加载用户列表返回DataGridView
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(SysUserVo userVo) {
        return this.sysUserService.queryAllUser(userVo);
    }


    /**
     * 添加用户
     */
    @RequestMapping("addUser")
    public ResultObj addUser(SysUserVo userVo) {
        try {
            this.sysUserService.addUser(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     */
    @RequestMapping("updateUser")
    public ResultObj updateUser(SysUserVo userVo) {
        try {
            this.sysUserService.updateUser(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除用户
     */
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(SysUserVo userVo) {
        try {
            this.sysUserService.deleteUser(userVo.getUserid());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除用户
     */
    @RequestMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(SysUserVo userVo) {
        try {
            this.sysUserService.deleteBatchUser(userVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    /**
     * 重置用户密码
     */
    @RequestMapping("resetUserPwd")
    public ResultObj resetUserPwd(SysUserVo userVo) {
        try {
            this.sysUserService.resetUserPwd(userVo.getUserid());
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 加载用户管理的分配角色数据
     */
    @RequestMapping("initUserRole")
    public DataGridView initUserRole(SysUserVo sysUserVo){
        return this.sysUserService.queryUserRole(sysUserVo.getUserid());
    }

    /**
     * 保存用户和角色的关联关系
     */
    @RequestMapping("saveUserRole")
    public  ResultObj saveRoleUser(SysUserVo sysUserVo){
        try {
            this.sysUserService.saveRoleUser(sysUserVo);
            return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

}


