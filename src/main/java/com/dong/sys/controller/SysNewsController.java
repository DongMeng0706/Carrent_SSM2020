package com.dong.sys.controller;

/**
 * @Author:dm
 * @Date:2020/9/14 16:35
 * @Description:
 */

import com.dong.sys.domain.SysNews;
import com.dong.sys.domain.SysUser;
import com.dong.sys.service.SysNewsService;
import com.dong.sys.vo.SysNewsVo;
import com.dong.utils.DataGridView;
import com.dong.utils.ResultObj;
import com.dong.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 公告管理控制器
 *
 * @author LJH
 *
 */
@RestController
@RequestMapping("news")
public class SysNewsController {

    @Autowired
    private SysNewsService sysNewsService;

    /**
     * 加载公告列表返回DataGridView
     */
    @RequestMapping("loadAllNews")
    @ResponseBody
    public DataGridView loadAllNews(SysNewsVo newsVo) {
        return this.sysNewsService.queryAllNews(newsVo);
    }

    /**
     * 添加公告
     */
    @RequestMapping("addNews")
    public ResultObj addNews(SysNewsVo newsVo) {
        try {
            newsVo.setCreatetime(new Date());
            SysUser user = (SysUser) WebUtils.getHttpSession().getAttribute("user");
            newsVo.setOpername(user.getRealname());
            this.sysNewsService.addNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改公告
     */
    @RequestMapping("updateNews")
    public ResultObj updateNews(SysNewsVo newsVo) {
        try {
            this.sysNewsService.updateNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除公告
     */
    @RequestMapping("deleteNews")
    public ResultObj deleteNews(SysNewsVo newsVo) {
        try {
            this.sysNewsService.deleteNews(newsVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除公告
     */
    @RequestMapping("deleteBatchNews")
    public ResultObj deleteBatchNews(SysNewsVo newsVo) {
        try {
            this.sysNewsService.deleteBatchNews(newsVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据ID查询公告
     */
    @RequestMapping("loadNewsById")
    public SysNews loadNewsById(Integer id){
        return this.sysNewsService.queryNewsById(id);
    }

}