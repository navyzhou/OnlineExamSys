package com.yc.onlineexamsys.dao;

import java.util.List;

import com.yc.onlineexamsys.bean.RoleInfo;

public interface IRoleInfoDao {
	public List<RoleInfo> findByPage(int pageNo, int pageSize);
	
	public List<RoleInfo> findAll();
	
	public int getTotal();
}
