package com.yc.onlineexamsys.dao.impl;

import java.util.List;

import com.yc.onlineexamsys.bean.MajorInfo;
import com.yc.onlineexamsys.dao.DBHelper;
import com.yc.onlineexamsys.dao.IMajorInfoDao;

public class MajorInfoDaoImpl implements IMajorInfoDao {

	@Override
	public int addMajorInfo(String mname) {
		DBHelper db = new DBHelper();
		String sql = "insert into majorInfo(mid,mname,status) values(seq_majorInfo_mid.nextval,?,1)";
		return db.update(sql, mname);
	}

	@Override
	public int updateMajorInfo(String mid, String mname) {
		DBHelper db = new DBHelper();
		String sql = "update majorInfo set mname=? where mid=?";
		return db.update(sql, mname, mid);
	}

	@Override
	public int updateMajorInfo(String mids, int status) {
		DBHelper db = new DBHelper();
		if(mids != null && !mids.contains(" or ") && mids.contains(",")){
			String sql = "update majorInfo set status=? where mid in(" + mids + ")";
			return db.update(sql,status);
		}else{
			String sql = "update majorInfo set status=? where mid=?";
			return db.update(sql, status, mids);
		}
	}

	@Override
	public List<MajorInfo> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select mid,mname,status from majorInfo order by mid";
		return db.finds(sql, MajorInfo.class);
	}

	@Override
	public List<MajorInfo> findByPage(int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql = "select * from( select a.*,rownum rn from("
				+ " select mid,mname,status from majorInfo order by mid) a where rownum<=?) where rn>?";
		return db.finds(sql, MajorInfo.class, pageNo*pageSize, (pageNo-1)*pageSize);
	}

	@Override
	public int getTotal() {
		DBHelper db = new DBHelper();
		String sql = "select mid,mname,status from majorInfo order by mid";
		return db.getTotal(sql);
	}
}
