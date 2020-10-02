package com.dong.bus.controller;

import com.dong.bus.domain.Rent;
import com.dong.bus.service.CheckService;
import com.dong.bus.service.RentService;
import com.dong.bus.vo.CheckVo;
import com.dong.sys.constast.SysConstast;
import com.dong.utils.DataGridView;
import com.dong.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @Author:dm
 * @Date:2020/9/23 20:40
 * @Description:
 */
@RestController
@RequestMapping("check")
public class CheckController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CheckService checkService;

    /***
     * 根据出租单号查询出租单信息
     */
    @RequestMapping("checkRentExist")
    public Rent checkRentExist(String rentid) {
        Rent rent=rentService.queryRentByRentId(rentid);//null   返回对象
        return rent;
    }


    /**
     * 根据出租单号加载检查单的表单数据
     */
    @RequestMapping("initCheckFormData")
    @ResponseBody
    public Map<String,Object> initCheckFormData(String rentid){
        return this.checkService.initCheckFormData(rentid);
    }

    /**
     * 车辆入库，保存检查单信息
     */
   /* @RequestMapping("saveCheck")
    public ResultObj saveCheck(CheckVo checkVo){
        try {
            System.out.println("=="+checkVo.getCheckdesc());
            this.checkService.saveCheck(checkVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }*/


    /**
     * 保存检查单数据
     */
    @RequestMapping("saveCheck")
    public ResultObj saveCheck(CheckVo checkVo) {
        try {
            checkVo.setCreatetime(new Date());
            this.checkService.saveCheck(checkVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询
     * @param checkVo
     * @return
     */
    @RequestMapping("loadAllCheck")
    public DataGridView loadAllCheck(CheckVo checkVo) {
        return this.checkService.queryAllCheck(checkVo);
    }

    /**
     * 修改检查单数据
     */
    @RequestMapping("updateCheck")
    public ResultObj updateCheck(CheckVo checkVo) {
        try {
            this.checkService.updateCheck(checkVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}
