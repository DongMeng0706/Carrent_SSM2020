package com.dong.sys.service;

import com.dong.sys.domain.SysMenu;
import com.dong.sys.vo.SysMenuVo;
import com.dong.utils.DataGridView;

import java.awt.*;
import java.util.List;

/**
 * @Author:dm
 * @Date:2020/9/13 10:42
 * @Description:
 */
public interface SysMenuService {

    /**
     * 查询所有菜单返回
     * List<Menu>
     */
    public List<SysMenu> queryAllMenuForList(SysMenuVo sysMenuVo);

    /**
     * 根据用户ID查询用户可用的菜单
     */
    public List<SysMenu> queryMenuByUserId(SysMenuVo sysMenuVo,Integer userId);

    /**
     * 查询所有菜单
     */
    public DataGridView queryAllMenu(SysMenuVo sysMenuVo);

    /**
     * 添加菜单
     */
    public void addMenu(SysMenuVo sysMenuVo);

    /**
     * 修改菜单
     */
    public  void updateMenu(SysMenuVo sysMenuVo);

    /**
     * 根据Pid菜单数量
     * @param  pid
     */
    public Integer queryMenuCountsByPid(Integer pid);

    /**
     * 根据ID删除菜单
     */
    public void deleteMenu(SysMenuVo sysMenuVo);

}
