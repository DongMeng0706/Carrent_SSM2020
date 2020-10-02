package com.dong.bus.service;

import com.dong.bus.domain.BusCustomer;
import com.dong.bus.vo.BusCustomerVo;
import com.dong.utils.DataGridView;

/**
 * @Author:dm
 * @Date:2020/9/16 20:56
 * @Description:
 */
public interface BusCustomerService {
    /**
     * 查询所有客户
     * @param customerVo
     * @return
     */
    public DataGridView queryAllCustomer(BusCustomerVo customerVo);
    /**
     * 添加客户
     * @param customerVo
     */
    public void addCustomer(BusCustomerVo customerVo);
    /**
     * 修改客户
     * @param customerVo
     */
    public void updateCustomer(BusCustomerVo customerVo);
    /**
     * 根据id删除客户
     * @param customerid
     */
    public void deleteCustomer(String identity);
    /**
     * 批量删除客户
     * @param customerVo
     */
    public void deleteBatchCustomer(String [] identitys);

    /**
     * 根据身份证号删除信息
     */
    public BusCustomer queryCustomerByIdcard(String identity);

}
