package com.dong.bus.service.impl;

import com.dong.bus.domain.BusCustomer;
import com.dong.bus.domain.Car;
import com.dong.bus.domain.Check;
import com.dong.bus.domain.Rent;
import com.dong.bus.mapper.BusCustomerMapper;
import com.dong.bus.mapper.CarMapper;
import com.dong.bus.mapper.CheckMapper;
import com.dong.bus.mapper.RentMapper;
import com.dong.bus.service.CheckService;
import com.dong.bus.vo.CheckVo;
import com.dong.sys.constast.SysConstast;
import com.dong.sys.domain.SysUser;
import com.dong.utils.DataGridView;
import com.dong.utils.RandomUtils;
import com.dong.utils.WebUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:dm
 * @Date:2020/9/23 20:34
 * @Description:
 */
@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckMapper checkMapper;
    @Autowired
    private BusCustomerMapper busCustomerMapper;

    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private CarMapper carMapper;


    @Override
    public Map<String, Object> initCheckFormData(String rentId) {
        //查询出租单
        Rent rent=this.rentMapper.selectByPrimaryKey(rentId);
        //查询客户
        BusCustomer customer=this.busCustomerMapper.selectByPrimaryKey(rent.getIdentity());
        //查询车辆
        Car car=this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        //组装Check
        Check check=new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC));
        check.setRentid(rentId);
        check.setCheckdate(new Date());
        SysUser user=(SysUser) WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());

        Map<String, Object> map=new HashMap<String, Object>();

        map.put("rent", rent);
        map.put("customer", customer);
        map.put("car", car);
        map.put("check", check);

        return map;

    }

    @Override
    public void saveCheck(CheckVo checkVO) {
        /**
         * 1.保存
         */
        this.checkMapper.insertSelective(checkVO);
        /**
         * 2.更改出租单状态
         */
        Rent rent = this.rentMapper.selectByPrimaryKey(checkVO.getRentid());
        rent.setRentflag(SysConstast.RENT_BACK_TRUE);
        this.rentMapper.updateByPrimaryKeySelective(rent);
        /**
         * 3.更改车辆状态
         **/
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);


    }

    @Override
    public DataGridView queryAllCheck(CheckVo checkVo) {
        Page<Object> page=PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        List<Check> data = this.checkMapper.queryAllCheck(checkVo);
        return new DataGridView(page.getTotal(), data);
    }
    @Override
    public void updateCheck(CheckVo checkVo) {
        this.checkMapper.updateByPrimaryKeySelective(checkVo);
    }
}
