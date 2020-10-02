package com.dong.bus.service;

import com.dong.bus.domain.Rent;
import com.dong.bus.vo.RentVO;
import com.dong.utils.DataGridView;

/**
 * @Author:dm
 * @Date:2020/9/20 13:13
 * @Description:
 */
public interface RentService {

    /**
     * 1.保存出租单
     */
    void addRent(RentVO rentVO);

    /**
     * 2.查询所有出租单
     */
    DataGridView queryAllRent(RentVO rentVO);

    /**
     * 3.修改出租单
     */
    void updateRent(RentVO rentVO);

    /**
     * 4.删除出租单
     */
    void deleteRent(String rentid);

    /**
     * 5.根据出租单号查询出租单信息
     */
    Rent queryRentByRentId(String rentId);

}
