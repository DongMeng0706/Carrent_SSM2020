package com.dong.bus.service.impl;

import com.dong.bus.domain.Car;
import com.dong.bus.domain.Rent;
import com.dong.bus.mapper.CarMapper;
import com.dong.bus.mapper.RentMapper;
import com.dong.bus.service.RentService;
import com.dong.bus.vo.RentVO;
import com.dong.sys.constast.SysConstast;
import com.dong.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:dm
 * @Date:2020/9/20 13:16
 * @Description:
 */
@Service
public class RentServiceimpl implements RentService {

    @Autowired
    private RentMapper rentMapper;
    @Autowired
    private CarMapper carMapper;

    @Override
    public void addRent(RentVO rentVO) {
        this.rentMapper.insertSelective(rentVO);
        //更改车辆的出租状态
        Car car = new Car();
        car.setCarnumber(rentVO.getCarnumber());
        car.setIsrenting(SysConstast.RENT_CAR_TRUE);
        this.carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public DataGridView queryAllRent(RentVO rentVO) {
        Page<Object> page = PageHelper.startPage(rentVO.getPage(),rentVO.getLimit());
        List<Rent> data = this.rentMapper.queryAllRentList(rentVO);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void updateRent(RentVO rentVO) {
        this.rentMapper.updateByPrimaryKeySelective(rentVO);
    }

    @Override
    public void deleteRent(String rentid) {
        //删除之前更改车辆出租状态 --为未出租
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);
        //删除
        this.rentMapper.deleteByPrimaryKey(rentid);
    }

    @Override
    public Rent queryRentByRentId(String rentId) {
        return this.rentMapper.selectByPrimaryKey(rentId);
    }


}
