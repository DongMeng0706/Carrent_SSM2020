package com.dong.sys.service.impl;

import com.dong.sys.domain.SysLogLogin;
import com.dong.sys.mapper.SysLogLoginMapper;
import com.dong.sys.service.SysLogLoginService;
import com.dong.sys.vo.SysLogLoginVo;
import com.dong.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.omg.PortableServer.ForwardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:dm
 * @Date:2020/9/14 14:54
 * @Description:
 */
@Service
public class SysLogLoginServiceImpl implements SysLogLoginService {

    @Autowired
    private SysLogLoginMapper sysLogLoginMapper;

    @Override
    public DataGridView queryAllLogInfo(SysLogLoginVo logInfoVo) {
        Page<Object> page=PageHelper.startPage(logInfoVo.getPage(), logInfoVo.getLimit());
        List<SysLogLogin> data = this.sysLogLoginMapper.queryAllLogLogin(logInfoVo);
        for (SysLogLogin datum : data) {
            System.err.println(datum.toString());
        }
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addLogInfo(SysLogLoginVo sysLogLoginVo) {
        this.sysLogLoginMapper.insertSelective(sysLogLoginVo);
    }

    @Override
    public void deleteLogInfo(Integer logInfoid) {
        this.sysLogLoginMapper.deleteByPrimaryKey(logInfoid);
    }

    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        for (Integer integer : ids) {
            this.deleteLogInfo(integer);
        }
    }


}
