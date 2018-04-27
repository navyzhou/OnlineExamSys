package com.yc.onlineexamsys.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.RoleInfo;
import com.yc.onlineexamsys.biz.IRoleInfoBiz;
import com.yc.onlineexamsys.dao.IRoleInfoDao;
import com.yc.onlineexamsys.dao.impl.RoleInfoDaoImpl;

public class RoleInfoBizImpl implements IRoleInfoBiz{

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		IRoleInfoDao roleInfoDao = new RoleInfoDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", roleInfoDao.findByPage(pageNo, pageSize));
		map.put("total", roleInfoDao.getTotal());
		return map;
	}

	@Override
	public List<RoleInfo> findAll() {
		IRoleInfoDao roleInfoDao = new RoleInfoDaoImpl();
		return roleInfoDao.findAll();
	}
}
