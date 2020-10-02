package com.dong.sys.vo;

import com.dong.sys.domain.SysMenu;

/**
 * @Author:dm
 * @Date:2020/9/13 10:40
 * @Description:
 */
public class SysMenuVo extends SysMenu {

    /**
     * 分页参数
     */
    private Integer page;
    private  Integer limit;

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
}
