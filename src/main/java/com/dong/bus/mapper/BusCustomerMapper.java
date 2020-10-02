package com.dong.bus.mapper;

import com.dong.bus.domain.BusCustomer;

import java.util.List;

public interface BusCustomerMapper {
    int deleteByPrimaryKey(String identity);

    int insert(BusCustomer record);

    int insertSelective(BusCustomer record);

    BusCustomer selectByPrimaryKey(String identity);

    int updateByPrimaryKeySelective(BusCustomer record);

    int updateByPrimaryKey(BusCustomer record);

    /**
     * 1.查询全部
     */
    List<BusCustomer> queryAllCustoerList(BusCustomer busCustomer);
}