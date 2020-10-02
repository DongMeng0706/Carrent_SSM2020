package com.dong.bus.vo;

import com.dong.bus.domain.BusCustomer;

/**
 * @Author:dm
 * @Date:2020/9/16 20:54
 * @Description:
 */
public class BusCustomerVo extends BusCustomer {

    /**
     * 分页参数
     */
    private  Integer page;
    private  Integer limit;

    /**
     * 接收多个id
     */
    private  String [] ids;

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

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
