package com.dong.sys.mapper;

import com.dong.sys.domain.SysMenu;
import com.dong.sys.vo.SysMenuVo;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /**
     * 查询所有的菜单
     * @return  List
     */
    List<SysMenu> queryAllMenu(SysMenu sysMenu);

    int queryMenuCountsByPid(Integer pid);

    /**
     * 根据角色ID查询菜单
     * @param  available
     * @param rid
     * @return
     */
    List<SysMenu> queryMenuByRoleId(@Param("available")Integer available,@Param("rid")Integer rid);

    /**
     * 根据用户id 查询菜单
     * sys_user
     * sys_role_user
     * sys_role_menu
     * sys_menu
     */
    List<SysMenu> queryMenuListByUserId(@Param("available")Integer available, @Param("userId") Integer userId);
}