package com.dong.sys.mapper;

import com.dong.sys.domain.SysUser;
import com.dong.sys.vo.SysUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 登陆
     * **/
    SysUser login(SysUser sysUser);

    /**
     * 查询全部用户
     */
    List<SysUser> queryAllUserList(SysUserVo sysUserVo);

    /**
     * 保存用户和角色的关系
     */
    void insertUserRole(@Param("userId")Integer userId,@Param("rid")Integer rid);
}