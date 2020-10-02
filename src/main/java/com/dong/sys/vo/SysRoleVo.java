package com.dong.sys.vo;

import com.dong.sys.domain.SysRole;

/**
 * @Author:dm
 * @Date:2020/9/13 14:44
 * @Description:
 */
public class SysRoleVo extends SysRole {

    /**
     * 分页
     */
    private Integer page;
    private Integer limit;

    //接受多个角色id
    private Integer [] ids;

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
}
