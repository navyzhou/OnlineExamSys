package com.yc.onlineexamsys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.onlineexamsys.bean.AdminInfos;
import com.yc.onlineexamsys.dao.DBHelper;
import com.yc.onlineexamsys.dao.IAdminInfoDao;
import com.yc.onlineexamsys.util.StringUtil;

public class AdminInfoDaoImpl implements IAdminInfoDao{

	@Override
	public int add(String aname, String pwd, String email, String photo) {
		DBHelper db = new DBHelper();
		String sql = "insert into adminInfos values(seq_adminInfos_aid.nextval,2,?,?,?,?,1)";
		return db.update(sql, aname, pwd, email, photo);
	}

	@Override
	public AdminInfos login(String account, String pwd) {
		DBHelper db = new DBHelper();
		String sql = "select aid, rid, aname, pwd, email, photo, status from adminInfos where (aid=? or email=?) and pwd=?";
		return db.find(sql, AdminInfos.class, account, account, pwd);
	}

	@Override
	public List<AdminInfos> findByPage(String aname, int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql = "select * from (select b.*,rownum rn from (select aid,a.rid,aname,pwd,email,photo,a.status,rname from adminInfos a,roleInfo r where a.rid=r.rid";
		List<Object> params = new  ArrayList<Object>();
		if (!StringUtil.isNull(aname)) {
			sql += " and aname like '%'||?||'%'";
			params.add(aname);
		}
		sql += " order by aid)  b where rownum<=?) where rn>? ";
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.gets(sql, AdminInfos.class, params);
	}

	@Override
	public int getTotal(String aname) {
		DBHelper db = new DBHelper();
		String sql = "select count(aid) from adminInfos";
		List<Object> params = new  ArrayList<Object>();
		if (!StringUtil.isNull(aname)) {
			sql += " where aname like '%'||?||'%'";
			params.add(aname);
		}
		return db.getTotal(sql, params);
	}

	@Override
	public int getAid(String email) {
		DBHelper db = new DBHelper();
		String sql = "select aid from adminInfos where email=?";
		return db.getTotal(sql,email);
	}
}
