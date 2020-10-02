package com.dong.sys.controller;

/**
 * @Author:dm
 * @Date:2020/9/14 15:09
 * @Description:
 */

import com.dong.sys.service.SysLogLoginService;
import com.dong.sys.vo.SysLogLoginVo;
import com.dong.utils.DataGridView;
import com.dong.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理控制器
 *
 * @author LJH
 *
 */
@RestController
@RequestMapping("logInfo")
public class LogInfoController {

    @Autowired
    private SysLogLoginService sysLogLoginService;

    /**
     * 加载日志列表返回DataGridView
     */
    @RequestMapping("loadAllLogInfo")
    public DataGridView loadAllLogInfo(SysLogLoginVo logInfoVo) {
        return this.sysLogLoginService.queryAllLogInfo(logInfoVo);
    }

    /**
     * 删除日志
     */
    @RequestMapping("deleteLogInfo")
    public ResultObj deleteLogInfo(SysLogLoginVo logInfoVo) {
        try {
            this.sysLogLoginService.deleteLogInfo(logInfoVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除日志
     */
    @RequestMapping("deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(SysLogLoginVo logInfoVo) {
        try {
            System.err.println("删除Ids="+logInfoVo.getIds());
            this.sysLogLoginService.deleteBatchLogInfo(logInfoVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
