package com.dong.sys.vo;

import com.dong.sys.domain.SysUser;

/**
 * @Author:dm
 * @Date:2020/9/13 8:54
 * @Description:
 */
public class SysUserVo extends SysUser {

    private Integer page;
    private Integer limit;

    private String code;
    /**
     * 接受多个角色id
     */
    private Integer[] ids;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
