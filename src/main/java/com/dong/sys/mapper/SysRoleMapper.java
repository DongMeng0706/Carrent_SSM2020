package com.dong.sys.mapper;

import com.dong.sys.domain.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 查询角色集合
     */
    List<SysRole> queryAllRole(SysRole sysRole);

    /**
     * 根据角色ID 删除 sys_role_menu关联表
     *
     */
    void deleteRoleMenuByRid(Integer roleid);

    /**
     * 根据角色ID 删除 sys_role_
     */
    void deleteRoleUserByRid(Integer roleid);

    /**
     * 保存角色和菜单的关系 sys_role)menu
     * @param rid
     * @param mid
     */
    void insertRoleMenu(@Param("rid")Integer rid,@Param("mid")Integer mid);

    /**
     * 根据用户ID删除 sys_role_user 关联
     */
    void deleteRoleUserByUserId(Integer userId);

    /**
     * 根据用户ID 查询角色
     */
    List<SysRole> queryRoleListByUserId(@Param("available") Integer available,@Param("userId") Integer userId);

}