package com.dong.sys.mapper;

import com.dong.sys.domain.SysLogLogin;
import com.dong.sys.vo.SysLogLoginVo;

import java.util.List;

public interface SysLogLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLogLogin record);

    int insertSelective(SysLogLogin record);

    SysLogLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLogLogin record);

    int updateByPrimaryKey(SysLogLogin record);

    /**
     * 1.查询所有日志
     */
    List<SysLogLogin> queryAllLogLogin(SysLogLoginVo sysLogLoginVo);
}