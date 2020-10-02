package com.dong.sys.service;

import com.dong.sys.vo.SysLogLoginVo;
import com.dong.utils.DataGridView;

/**
 * @Author:dm
 * @Date:2020/9/14 14:46
 * @Description:
 */
public interface SysLogLoginService {

    /**
     * 查询所有日志
     * @param logInfoVo
     * @return
     */
    public DataGridView queryAllLogInfo(SysLogLoginVo logInfoVo);
    /**
     * 添加日志
     * @param logInfoVo
     */
    public void addLogInfo(SysLogLoginVo logInfoVo);
    /**
     * 根据id删除日志
     * @param logInfoid
     */
    public void deleteLogInfo(Integer logInfoid);
    /**
     * 批量删除日志
     * @param logInfoVo
     */
    public void deleteBatchLogInfo(Integer [] ids);
}
