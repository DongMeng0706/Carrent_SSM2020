package com.dong.bus.mapper;

import com.dong.bus.domain.Rent;
import com.dong.bus.vo.RentVO;

import java.util.List;

public interface RentMapper {
    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);

    int insertSelective(Rent record);

    Rent selectByPrimaryKey(String rentid);

    int updateByPrimaryKeySelective(Rent record);

    int updateByPrimaryKey(Rent record);

    /**
     * 1.查询所有出租单
     */
    public List<Rent> queryAllRentList(RentVO rentVO);
}