package com.dong.sys.service.impl;

import com.dong.sys.constast.SysConstast;
import com.dong.sys.domain.SysMenu;
import com.dong.sys.domain.SysRole;
import com.dong.sys.mapper.SysMenuMapper;
import com.dong.sys.mapper.SysRoleMapper;
import com.dong.sys.service.SysRoleService;
import com.dong.sys.vo.SysRoleVo;
import com.dong.utils.DataGridView;
import com.dong.utils.TreeNode;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author:dm
 * @Date:2020/9/13 14:52
 * @Description:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public DataGridView queryAllRole(SysRoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        List<SysRole> data = this.sysRoleMapper.queryAllRole(roleVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addRole(SysRoleVo roleVo) {
        this.sysRoleMapper.insertSelective(roleVo);
    }

    @Override
    public void updateRole(SysRoleVo roleVo) {
        this.sysRoleMapper.updateByPrimaryKeySelective(roleVo);
    }

    @Override
    public void deleteRole(Integer roleid) {
        // 删除角色表的数据
        this.sysRoleMapper.deleteByPrimaryKey(roleid);
        // 根据角色id删除sys_role_role里面的数据
        this.sysRoleMapper.deleteRoleMenuByRid(roleid);
        //根据角色id删除sys_role_user里面的数据
        this.sysRoleMapper.deleteRoleUserByRid(roleid);

    }

    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer rid : ids) {
            deleteRole(rid);
        }
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        // 查询所有的可用的菜单
        SysMenu menu = new SysMenu();
        menu.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<SysMenu> allMenu = sysMenuMapper.queryAllMenu(menu);
        System.out.println("allMenu:==="+allMenu.size());

        // 根据角色ID查询当前角色拥有的菜单
        List<SysMenu> roleMenu = sysMenuMapper.queryMenuByRoleId(SysConstast.AVAILABLE_TRUE, roleid);
        System.out.println("roleMenu:==="+roleMenu.size());

        List<TreeNode> data = new ArrayList<>();
        for (SysMenu m1 : allMenu) {
            String checkArr = SysConstast.CODE_ZERO+"";
            for (SysMenu m2 : roleMenu) {
                if (m1.getId() == m2.getId()) {
                    checkArr = SysConstast.CODE_ONE+"";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            data.add(new TreeNode(id, pid, title, spread, checkArr));
        }

        for (TreeNode datum : data) {
            System.err.println(datum.toString());
        }

        return new DataGridView(data);

    }

    @Override
    public void saveRoleMenu(SysRoleVo sysRoleVo) {
        Integer rid=sysRoleVo.getRoleid();
        Integer [] mids=sysRoleVo.getIds();
        //根据rid删除sys_role_menu里面所有数据
        this.sysRoleMapper.deleteRoleMenuByRid(rid);
        //保存角色和菜单的关系
        for (Integer mid : mids) {
            this.sysRoleMapper.insertRoleMenu(rid,mid);
        }
    }


}
