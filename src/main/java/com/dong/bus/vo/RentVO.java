package com.dong.bus.vo;

import com.dong.bus.domain.Rent;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author:dm
 * @Date:2020/9/20 12:15
 * @Description:
 */
public class RentVO extends Rent {

    /**
     * 分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 时间查询参数
     * @return
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

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
