package com.dong.sys.mapper;

import com.dong.sys.domain.SysNews;
import com.dong.sys.vo.SysNewsVo;

import java.util.List;

public interface SysNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysNews record);

    int insertSelective(SysNews record);

    SysNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysNews record);

    int updateByPrimaryKey(SysNews record);

    /**
     * 1.查询所有公告集合
     */
    List<SysNews> queryAllNewsList(SysNews sysNews);
}