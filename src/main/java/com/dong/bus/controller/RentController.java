package com.dong.bus.controller;

import com.dong.bus.domain.BusCustomer;
import com.dong.bus.service.BusCustomerService;
import com.dong.bus.service.CarService;
import com.dong.bus.service.RentService;
import com.dong.bus.vo.RentVO;
import com.dong.sys.constast.SysConstast;
import com.dong.sys.domain.SysUser;
import com.dong.utils.DataGridView;
import com.dong.utils.RandomUtils;
import com.dong.utils.ResultObj;
import com.dong.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author:dm
 * @Date:2020/9/20 12:21
 * @Description:
 */
@RestController
@RequestMapping("rent")
public class RentController {

    @Autowired
    private BusCustomerService busCustomerService;

    @Autowired
    private RentService rentService;

    /**
     * 1.检查身份证号是否存在
     */
    @RequestMapping("checkCustomerExist")
    public ResultObj checkCustomerExist(RentVO rentVO){
        BusCustomer customer = this.busCustomerService.queryCustomerByIdcard(rentVO.getIdentity());
        if(null!=customer){
            return ResultObj.STATUS_TRUE;
        }else{
            return ResultObj.STATUS_FALSE;
        }
    }

    /**
     * 初始化添加出租单的表单数据
     */
    @RequestMapping("initRentFrom")
    public RentVO initRentFrom(RentVO rentVo) {
        //生成出租单号
        rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_CZ));
        //设置起租时间
        rentVo.setBegindate(new Date());

        SysUser user=(SysUser) WebUtils.getHttpSession().getAttribute("user");
        //设置操作员
        rentVo.setOpername(user.getRealname());
        return rentVo;
    }

    /**
     * 保存出租单信息
     */
    @RequestMapping("saveRent")
    public ResultObj saveRent(RentVO rentVo) {
        try {
            //设置创建时间
            rentVo.setCreatetime(new Date());
            //设置归还状态
            rentVo.setRentflag(SysConstast.RENT_BACK_FALSE);

            //保存
            this.rentService.addRent(rentVo);

            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询全部
     */
    @RequestMapping("loadAllRent")
    public DataGridView loadAllRentList(RentVO rentVO){
        System.out.println("=======rentVO:"+rentVO.getRentflag());
        return  this.rentService.queryAllRent(rentVO);
    }

    /**
     * 修改出租单信息
     */
    @RequestMapping("updateRent")
    public ResultObj updateRent(RentVO rentVO){
        try {
            this.rentService.updateRent(rentVO);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除
     */
    @RequestMapping("deleteRent")
    public ResultObj deleteRent(RentVO rentVO){
        try {
            this.rentService.deleteRent(rentVO.getRentid());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
