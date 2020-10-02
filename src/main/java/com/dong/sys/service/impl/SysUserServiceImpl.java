package com.dong.sys.service.impl;

import com.dong.sys.constast.SysConstast;
import com.dong.sys.domain.SysRole;
import com.dong.sys.domain.SysUser;
import com.dong.sys.mapper.SysRoleMapper;
import com.dong.sys.mapper.SysUserMapper;
import com.dong.sys.service.SysUserService;
import com.dong.sys.vo.SysUserVo;
import com.dong.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:dm
 * @Date:2020/9/13 8:57
 * @Description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysUser login(SysUserVo sysUserVo) {
        //明文12345
        //生成密文
        String pwd =DigestUtils.md5DigestAsHex(sysUserVo.getPwd().getBytes());
        sysUserVo.setPwd(pwd);

        return sysUserMapper.login(sysUserVo);
    }

    @Override
    public DataGridView queryAllUser(SysUserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<SysUser> data = this.sysUserMapper.queryAllUserList(userVo);
        return new DataGridView(page.getTotal(), data);
    }

    @Override
    public void addUser(SysUserVo userVo) {
        // 设置默认密码
        userVo.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        // 设置用户类型 都是普通用户type=2
        userVo.setType(SysConstast.USER_TYPE_NOMAL);
        this.sysUserMapper.insertSelective(userVo);

    }

    @Override
    public void updateUser(SysUserVo userVo) {
        this.sysUserMapper.updateByPrimaryKeySelective(userVo);
    }

    @Override
    public void deleteUser(Integer userid) {
        // 删除用户
        this.sysUserMapper.deleteByPrimaryKey(userid);
        // 根据用户id删除sys_role_user里面的数据
        this.sysRoleMapper.deleteRoleUserByUserId(userid);
    }

    @Override
    public void deleteBatchUser(Integer[] ids) {
        for (Integer uid : ids) {
            this.deleteUser(uid);
        }
    }

    @Override
    public void resetUserPwd(Integer userid) {
        SysUser user = new SysUser();
        user.setUserid(userid);
        // 设置默认密码
        user.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
        //更新
        this.sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public DataGridView queryUserRole(Integer userid) {
        //1,查询所有可用的角色
        SysRole role=new SysRole();
        role.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<SysRole> allRole=this.sysRoleMapper.queryAllRole(role);
        //2,根据用户ID查询已拥有的角色
        List<SysRole> userRole=this.sysRoleMapper.queryRoleListByUserId(SysConstast.AVAILABLE_TRUE,userid);

        List<Map<String,Object>> data=new ArrayList<>();
        for (SysRole r1 : allRole) {
            Boolean LAY_CHECKED=false;
            for (SysRole r2 : userRole) {
                if(r1.getRoleid()==r2.getRoleid()) {
                    LAY_CHECKED=true;
                }
            }
            Map<String, Object> map=new HashMap<String, Object>();

            map.put("roleid", r1.getRoleid());
            map.put("rolename", r1.getRolename());
            map.put("roledesc", r1.getRoledesc());
            map.put("LAY_CHECKED", LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }



    @Override
    public void saveRoleUser(SysUserVo userVo) {
        Integer userid = userVo.getUserid();
        Integer[] roleIds = userVo.getIds();
        //根据用户id删除sys_role_user里面的数据
        this.sysRoleMapper.deleteRoleUserByUserId(userid);
        //保存关系
        if(roleIds!=null&& roleIds.length>0) {
            for (Integer rid : roleIds) {
                this.sysUserMapper.insertUserRole(userid,rid);
            }
        }
    }




}
