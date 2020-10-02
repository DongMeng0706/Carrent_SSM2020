package com.dong.bus.service;

import com.dong.bus.domain.Car;
import com.dong.bus.vo.CarVo;
import com.dong.utils.DataGridView;

/**
 * @Author:dm
 * @Date:2020/9/16 21:41
 * @Description:
 */
public interface CarService {

    /**
     * 1.查询所有车辆
     */
    public DataGridView queryAllCar(CarVo carVo);

    /**
     * 2.新增车辆
     */
    public  void addCar(CarVo carVo);

    /**
     * 3.修改车辆
     */
    public  void updateCar(CarVo carVo);

    /**
     * 4.根据id删除车辆
     */
    public  void deleteCarById(String carnumber);

    /**
     * 5.批量删除
     */
    public  void deleteBatchCar(String[] carnumbers);

    public Car queryCarByCarNumber(String carnumber);

}
