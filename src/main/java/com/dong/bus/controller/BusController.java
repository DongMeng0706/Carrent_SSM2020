package com.dong.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:dm
 * @Date:2020/9/16 21:11
 * @Description:
 */
@Controller
@RequestMapping("bus")
public class BusController {
    /**
     * 1.跳转到客户管理的页面
     */
    @RequestMapping("toCustomerManager")
    public String toCustomerManager() {
        return "business/customer/customerManager";
    }

    /**
     * 测试远程提交  修改与20201002
     */

    /**
     * 1.跳转到车辆管理的页面
     */
    @RequestMapping("toCarManager")
    public String toCarManager() {
        return "business/car/carManager";
    }


    /**
     * 跳转到车辆出租的页面
     */
    @RequestMapping("toRentCarManager")
    public String toRentCarManager() {
        return "business/rent/rentCarManager";
    }

    /**
     * 跳转到出租单管理的页面
     */
    @RequestMapping("toRentManager")
    public String toRentManager() {
        return "business/rent/rentManager";
    }

    /**
     * 跳转到汽车入库管理的页面
     */
    @RequestMapping("toCheckCarManager")
    public String toCheckCarManager() {
        return "business/check/checkCarManager";
    }

    /**
     * 跳转到检查单管理的页面
     */
    @RequestMapping("toCheckManager")
    public String toCheckManager() {
        return "business/check/checkManager";
    }
}
