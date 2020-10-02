package com.dong.bus.controller;

import com.dong.bus.service.BusCustomerService;
import com.dong.bus.vo.BusCustomerVo;
import com.dong.utils.DataGridView;
import com.dong.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author:dm
 * @Date:2020/9/16 21:00
 * @Description:
 */
@RestController
@RequestMapping("customer")
public class BusCustomerController {

    @Autowired
    private BusCustomerService busCustomerService;

    /**
     * 加载客户列表返回DataGridView
     */
    @RequestMapping("loadAllCustomer")
    public DataGridView loadAllCustomer(BusCustomerVo customerVo) {
        return this.busCustomerService.queryAllCustomer(customerVo);
    }

    /**
     * 添加客户
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(BusCustomerVo customerVo) {
        try {
            customerVo.setCreatetime(new Date());
            this.busCustomerService.addCustomer(customerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改客户
     */
    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(BusCustomerVo customerVo) {
        try {
            this.busCustomerService.updateCustomer(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除客户
     */
    @RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(BusCustomerVo customerVo) {
        try {
            this.busCustomerService.deleteCustomer(customerVo.getIdentity());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除客户
     */
    @RequestMapping("deleteBatchCustomer")
    public ResultObj deleteBatchCustomer(BusCustomerVo customerVo) {
        try {
            this.busCustomerService.deleteBatchCustomer(customerVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
