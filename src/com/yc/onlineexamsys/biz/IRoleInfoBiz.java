package com.yc.onlineexamsys.biz;

import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.RoleInfo;

public interface IRoleInfoBiz {
	public Map<String, Object> findByPage(int pageNo, int pageSize);
	
	public List<RoleInfo> findAll();
}
