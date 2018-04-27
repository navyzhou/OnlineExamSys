package com.yc.onlineexamsys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.onlineexamsys.bean.ClassInfo;
import com.yc.onlineexamsys.dao.DBHelper;
import com.yc.onlineexamsys.dao.IClassInfoDao;
import com.yc.onlineexamsys.util.StringUtil;

public class ClassInfoDaoImpl implements IClassInfoDao {
	@Override
	public int addClassInfo(String cname, String mid, String grade, String length) {
		DBHelper db = new DBHelper();
		String sql = "insert into classInfo values(seq_classInfo_cid.nextval,?,?,?,?,1)";
		return db.update(sql, cname, mid, grade, length);
	}

	@Override
	public int updateClassInfo(String cid, String cname, String mid, String grade, String length) {
		DBHelper db = new DBHelper();
		String sql = "update classInfo set cname=?, mid=?, grade=?, length=? where cid=?";
		return db.update(sql, cname, mid, grade, length, cid);
	}

	@Override
	public int updateClassInfo(String cids, int status) {
		DBHelper db = new DBHelper();
		if (cids != null && cids.contains(",") && !cids.contains(" or")){
			String sql = "update classInfo set status=? where cid in(" + cids +")";
			return db.update(sql,status);
		} else {
			String sql = "update classInfo set status=? where cid=?";
			return db.update(sql, status, cids);
		}
	}
	
	public List<ClassInfo> finds(){
		DBHelper db = new DBHelper();
		String sql = "select cid, cname, mid, grade from classInfo";
		return db.finds(sql,ClassInfo.class);
	}

	@Override
	public List<ClassInfo> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select cid,cname,c.mid,mname,grade,length,status from classInfo c,majorInfo m where c.mid=m.mid order by cid";
		return db.finds(sql, ClassInfo.class);
	}

	@Override
	public List<ClassInfo> findByPage(int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
//		String sql = "select * from (select a.*, rownum rn from ("
//				+ "select cid,cname,c.mid,mname,grade,length,status from classInfo c,marjorInfo m "
//				+ " where c.mid=m.mid order by cid) a where rownum <= ?) where rn>?";
		String sql = "select * from (select a.*, rownum rn from ("
				+ "select cid,cname,mid,grade,length,status from classInfo c order by cid) a where rownum <= ?) where rn>?";

		return db.finds(sql, ClassInfo.class, pageNo*pageSize, (pageNo-1)*pageSize);
	}

	@Override
	public List<ClassInfo> find(String grade, String mid, String length) {
		DBHelper db = new DBHelper();
		String sql = "select cid,cname,c.mid,mname,grade,length,status from classInfo c,majorInfo m where c.mid=m.mid";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(grade)){
			sql += " and grade=?";
			params.add(grade);
		}
		if (!StringUtil.isNull(mid)){
			sql += " and c.mid=?";
			params.add(mid);
		}
		if (!StringUtil.isNull(length)){
			sql += " and length=?";
			params.add(length);
		}
		sql += " order by cid";

		return db.finds(sql, ClassInfo.class, params);
	}

	@Override
	public List<ClassInfo> find(String grade, String mid, String length, int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
//		String sql = "select * from (select a.*, rownum rn from ("
//				+ "select cid,cname,c.mid,mname,grade,length,status from classInfo c,marjorInfo m where c.mid=m.mid";
		String sql = "select * from (select a.*, rownum rn from ("
				+ "select cid,cname,mid,grade,length,status from classInfo where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(grade)){
			sql += " and grade=?";
			params.add(grade);
		}
		if (!StringUtil.isNull(mid)){
			sql += " and mid=?";
			params.add(mid);
		}
		if (!StringUtil.isNull(length)){
			sql += " and length=?";
			params.add(length);
		}
		sql += "order by cid) a where rownum <= ?) where rn>?";
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.finds(sql, ClassInfo.class, params);
	}

	@Override
	public int getTotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(cid) from classInfo";
		return db.getTotal(sql);
	}

	@Override
	public int getTotal(String grade, String mid, String length) {
		DBHelper db = new DBHelper();
		String sql = "select count(cid) from classInfo where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(grade)){
			sql += " and grade=?";
			params.add(grade);
		}
		if (!StringUtil.isNull(mid)){
			sql += " and mid=?";
			params.add(mid);
		}
		if (!StringUtil.isNull(length)){
			sql += " and length=?";
			params.add(length);
		}
		return db.getTotal(sql, params);
	}
}
