package com.dong.sys.service;

import com.dong.sys.domain.SysNews;
import com.dong.sys.vo.SysNewsVo;
import com.dong.utils.DataGridView;

/**
 * 公告管理的服务接口
 * @author LJH
 *
 */
public interface SysNewsService {
	/**
	 * 查询所有公告
	 * @param newsVo
	 * @return
	 */
	public DataGridView queryAllNews(SysNewsVo newsVo);
	/**
	 * 添加公告
	 * @param newsVo
	 */
	public void addNews(SysNewsVo newsVo);
	/**
	 * 修改公告
	 * @param newsVo
	 */
	public void updateNews(SysNewsVo newsVo);
	/**
	 * 根据id删除公告
	 * @param newsid
	 */
	public void deleteNews(Integer newsid);
	/**
	 * 批量删除公告
	 * @param
	 */
	public void deleteBatchNews(Integer[] ids);
	
	/**
	 * 查询一个公告
	 * @param id
	 * @return
	 */
	public SysNews queryNewsById(Integer id);

}
