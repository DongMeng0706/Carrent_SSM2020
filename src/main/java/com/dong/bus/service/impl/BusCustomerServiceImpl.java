package com.dong.bus.service.impl;

import com.dong.bus.domain.BusCustomer;
import com.dong.bus.mapper.BusCustomerMapper;
import com.dong.bus.service.BusCustomerService;
import com.dong.bus.vo.BusCustomerVo;
import com.dong.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:dm
 * @Date:2020/9/16 20:58
 * @Description:
 */
@Service
public class BusCustomerServiceImpl implements BusCustomerService {
    @Autowired
    private BusCustomerMapper busCustomerMapper;

    @Override
    public DataGridView queryAllCustomer(BusCustomerVo customerVo) {
        Page<Object> page=PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
        List<BusCustomer> data = this.busCustomerMapper.queryAllCustoerList(customerVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addCustomer(BusCustomerVo customerVo) {
        this.busCustomerMapper.insertSelective(customerVo);
    }

    @Override
    public void deleteCustomer(String identity) {
        this.busCustomerMapper.deleteByPrimaryKey(identity);
    }

    @Override
    public void deleteBatchCustomer(String[] identitys) {
        for (String identity : identitys) {
            this.deleteCustomer(identity);
        }
    }

    @Override
    public BusCustomer queryCustomerByIdcard(String identity) {
        return this.busCustomerMapper.selectByPrimaryKey(identity);
    }

    @Override
    public void updateCustomer(BusCustomerVo customerVo) {
        this.busCustomerMapper.updateByPrimaryKeySelective(customerVo);
    }


}
