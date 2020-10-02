package com.dong.bus.vo;

import com.dong.bus.domain.Car;

/**
 * @Author:dm
 * @Date:2020/9/16 21:38
 * @Description:
 */
public class CarVo extends Car {

    /**
     * 1.分页参数
     */
    private  Integer page;
    private  Integer limit;

    /**
     * 2.接收多个参数
     */
    private  String[] ids;


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
