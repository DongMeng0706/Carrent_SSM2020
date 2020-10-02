package com.dong.bus.service.impl;

import com.dong.bus.domain.Car;
import com.dong.bus.mapper.CarMapper;
import com.dong.bus.service.CarService;
import com.dong.bus.vo.CarVo;
import com.dong.sys.constast.SysConstast;
import com.dong.utils.AppFileUtils;
import com.dong.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:dm
 * @Date:2020/9/16 21:42
 * @Description:
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;


    @Override
    public DataGridView queryAllCar(CarVo carVo) {
        Page<Object> page = PageHelper.startPage(carVo.getPage(),carVo.getLimit());
        List<Car> data = this.carMapper.queryAllCarList(carVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addCar(CarVo carVo) {
        this.carMapper.insertSelective(carVo);
    }

    @Override
    public void updateCar(CarVo carVo) {
        this.carMapper.updateByPrimaryKeySelective(carVo);
    }

    @Override
    public void deleteCarById(String carnumber) {
        //1.删除的时候先删除图片
        Car car = this.carMapper.selectByPrimaryKey(carnumber);
        if(car.getCarimg()!=null && !car.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)){
            AppFileUtils.deleteFileUsePath(car.getCarimg());
        }
        //2.删除数据库的数据
        this.carMapper.deleteByPrimaryKey(carnumber);
    }

    @Override
    public void deleteBatchCar(String[] carnumbers) {
        for (String carnumber : carnumbers) {
            this.deleteCarById(carnumber);
        }
    }

    @Override
    public Car queryCarByCarNumber(String carnumber) {
        return this.carMapper.selectByPrimaryKey(carnumber);
    }
}
