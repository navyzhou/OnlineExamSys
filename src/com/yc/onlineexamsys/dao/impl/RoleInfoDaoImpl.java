package com.yc.onlineexamsys.dao.impl;

import java.util.List;

import com.yc.onlineexamsys.bean.RoleInfo;
import com.yc.onlineexamsys.dao.DBHelper;
import com.yc.onlineexamsys.dao.IRoleInfoDao;

public class RoleInfoDaoImpl implements IRoleInfoDao{

	@Override
	public List<RoleInfo> findByPage(int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql ="select * from (select a.*,rownum rn from (select rid, rname from roleInfo order by rid)a where rownum<=?) where rn>?";
		return db.finds(sql, RoleInfo.class, pageNo*pageSize, (pageNo-1)*pageSize);
	}

	@Override
	public List<RoleInfo> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select rid, rname from roleInfo";
		return db.finds(sql, RoleInfo.class);
	}

	@Override
	public int getTotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(rid) from roleInfo";
		return db.getTotal(sql);
	}
}
