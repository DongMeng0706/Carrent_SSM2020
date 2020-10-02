package com.dong.bus.vo;

import com.dong.bus.domain.Check;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author:dm
 * @Date:2020/9/23 20:26
 * @Description:
 */
public class CheckVo extends Check {

    /**
     * 1.分页参数
     */
    private Integer page;
    private Integer limit;

    /**
     * 2.查询参数  开始时间  结束时间
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
