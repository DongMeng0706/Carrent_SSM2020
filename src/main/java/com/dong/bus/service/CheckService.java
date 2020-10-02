package com.dong.bus.service;

import com.dong.bus.vo.CheckVo;
import com.dong.utils.DataGridView;

import java.util.Map;

/**
 * @Author:dm
 * @Date:2020/9/23 20:33
 * @Description:
 */
public interface CheckService {

    /**
     * 根据出租单号加载检查单表单数据
     * @param rentId
     * @return
     */
    Map<String,Object> initCheckFormData(String rentId);

    /**
     * 保存检查单
     */
    void saveCheck(CheckVo checkVO);

    /**
     * 查询
     */
    DataGridView queryAllCheck(CheckVo checkVo);

    /**
     * 修改检查单
     * @param checkVo
     */
    void updateCheck(CheckVo checkVo);

}
