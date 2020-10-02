package com.dong.sys.service;

import com.dong.sys.vo.SysRoleVo;
import com.dong.utils.DataGridView;

/**
 * @Author:dm
 * @Date:2020/9/13 14:48
 * @Description:
 */
public interface SysRoleService {

    /**
     * 查询全部角色
     */
    public DataGridView queryAllRole(SysRoleVo sysRoleVo);

    /**
     * 添加
     */
    public  void  addRole(SysRoleVo sysRoleVo);

    /**
     * 修改
     */
    public  void updateRole(SysRoleVo sysRoleVo);

    /**
     * 根据ID删除角色
     */
    public  void deleteRole(Integer roleid);

    /**
     * 批量删除
     */
    public  void deleteBatchRole(Integer[] ids);


    /**
     * 加载角色管理分配的Json
     */
    public DataGridView initRoleMenuTreeJson(Integer roleid);

    /**
     * 保存角色和菜单的关联关系
     */
    public  void saveRoleMenu(SysRoleVo sysRoleVo);
}
