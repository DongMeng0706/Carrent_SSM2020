package com.dong.sys.service.impl;

import java.util.List;

import com.dong.sys.domain.SysNews;
import com.dong.sys.mapper.SysNewsMapper;
import com.dong.sys.service.SysNewsService;
import com.dong.sys.vo.SysNewsVo;
import com.dong.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class SysNewsServiceImpl implements SysNewsService {
	
	@Autowired
	private SysNewsMapper sysNewsMapper;

	@Override
	public DataGridView queryAllNews(SysNewsVo newsVo) {
		Page<Object> page=PageHelper.startPage(newsVo.getPage(), newsVo.getLimit());
		List<SysNews> data = this.sysNewsMapper.queryAllNewsList(newsVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addNews(SysNewsVo newsVo) {
		this.sysNewsMapper.insertSelective(newsVo);
	}

	@Override
	public void deleteNews(Integer newsid) {
		this.sysNewsMapper.deleteByPrimaryKey(newsid);
	}

	@Override
	public void deleteBatchNews(Integer[] ids) {
		for (Integer integer : ids) {
			this.deleteNews(integer);
		}
	}

	@Override
	public void updateNews(SysNewsVo newsVo) {
		this.sysNewsMapper.updateByPrimaryKeySelective(newsVo);
	}

	@Override
	public SysNews queryNewsById(Integer id) {
		return this.sysNewsMapper.selectByPrimaryKey(id);
	}

}
