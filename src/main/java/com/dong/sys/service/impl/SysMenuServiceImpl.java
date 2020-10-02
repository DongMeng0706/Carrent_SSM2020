package com.dong.sys.service.impl;

import com.dong.sys.domain.SysMenu;
import com.dong.sys.mapper.SysMenuMapper;
import com.dong.sys.service.SysMenuService;
import com.dong.sys.vo.SysMenuVo;
import com.dong.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:dm
 * @Date:2020/9/13 10:45
 * @Description:
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> queryAllMenuForList(SysMenuVo sysMenuVo) {
        return sysMenuMapper.queryAllMenu(sysMenuVo);
    }

    @Override
    public List<SysMenu> queryMenuByUserId(SysMenuVo sysMenuVo, Integer userId) {
        return sysMenuMapper.queryMenuListByUserId(sysMenuVo.getAvailable(),userId);
    }

    @Override
    public DataGridView queryAllMenu(SysMenuVo sysMenuVo) {
        Page<Object> page = PageHelper.startPage(sysMenuVo.getPage(),sysMenuVo.getLimit());
        List<SysMenu> data = this.sysMenuMapper.queryAllMenu(sysMenuVo);
        System.err.println("data:"+data.size());
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addMenu(SysMenuVo sysMenuVo) {
        this.sysMenuMapper.insertSelective(sysMenuVo);
    }

    @Override
    public void updateMenu(SysMenuVo sysMenuVo) {
        this.sysMenuMapper.updateByPrimaryKeySelective(sysMenuVo);
    }

    @Override
    public Integer queryMenuCountsByPid(Integer pid) {
        return this.sysMenuMapper.queryMenuCountsByPid(pid);
    }

    @Override
    public void deleteMenu(SysMenuVo sysMenuVo) {
        //删除菜单表里面的栏目
        this.sysMenuMapper.deleteByPrimaryKey(sysMenuVo.getId());
    }
}
