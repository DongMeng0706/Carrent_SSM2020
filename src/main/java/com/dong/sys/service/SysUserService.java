package com.dong.sys.service;

import com.dong.sys.domain.SysUser;
import com.dong.sys.vo.SysUserVo;
import com.dong.utils.DataGridView;

/**
 * @Author:dm
 * @Date:2020/9/13 8:55
 * @Description:
 */
public interface SysUserService {

    /**
     * 登陆
     */
    SysUser login(SysUserVo sysUserVo);

    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    public DataGridView queryAllUser(SysUserVo userVo);

    /**
     * 添加用户
     * @param userVo
     */
    public void addUser(SysUserVo userVo);

    /**
     * 修改用户
     * @param userVo
     */
    public void updateUser(SysUserVo userVo);

    /**
     * 根据id删除用户
     * @param userid
     */
    public void deleteUser(Integer userid);
    /**
     * 批量删除用户
     * @param SysUserVo
     */
    public void deleteBatchUser(Integer [] ids);

    /**
     * 重置密码
     * @param userid
     */
    public void resetUserPwd(Integer userid);

    /**
     *根据用户id查询角色
     * @param userid
     * @return
     */
    DataGridView queryUserRole(Integer userid);

    /**
     * 保存用户和角色的关联
     */
    void saveRoleUser(SysUserVo sysUserVo);


}
