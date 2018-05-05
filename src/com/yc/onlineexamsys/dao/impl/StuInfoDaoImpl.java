package com.yc.onlineexamsys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.onlineexamsys.bean.ClassInfo;
import com.yc.onlineexamsys.bean.StuInfo;
import com.yc.onlineexamsys.dao.DBHelper;
import com.yc.onlineexamsys.dao.IStuInfoDao;
import com.yc.onlineexamsys.util.StringUtil;

public class StuInfoDaoImpl implements IStuInfoDao{
	@Override
	public List<ClassInfo> getInfo() {
		DBHelper db = new DBHelper();
		String sql="select mid cid,mname cname,0 as mid,0 as grade from majorInfo union select cid,cname,mid,grade from classInfo";
		return db.finds(sql,ClassInfo.class);
	}

	@Override
	public int addStuInfos(List<List<String>> list) {
		DBHelper db = new DBHelper();
		String sql = "insert into stuInfo values(?,?,?,?,?,?,?,?,1)";
		return db.updates(sql,list);
	}

	@Override
	public int addStuInfo(String sid, String sname, String pwd, String cid, String sex, String photo, String cardId, String tel) {
		DBHelper db = new DBHelper();
		String sql = "insert into stuInfo values(?,?,?,?,?,?,?,?,1)";
		return db.update(sql, sid, sname, pwd, cid, sex, photo, cardId, tel);
	}

	@Override
	public List<StuInfo> findByPage(int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*, rownum rn from(select sid,sname,sex,cardId,tel,cname,grade,mname,c.mid,s.cid,photo from stuInfo s,classInfo c,majorinfo m "
				+ " where s.cid=c.cid and c.mid=m.mid order by sid) a where rownum<=?) where rn>?";
		return db.finds(sql,StuInfo.class, pageNo*pageSize, (pageNo-1)*pageSize);
	}

	@Override
	public List<StuInfo> findByCondition(String sid, String sname, String mid, String cid, String grade, int pageNo,int pageSize) {
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*, rownum rn from(select sid,sname,sex,cardId,tel,cname,grade,mname,c.mid,s.cid,photo from stuInfo s,classInfo c,majorinfo m "
				+ " where s.cid=c.cid and c.mid=m.mid";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(sid)){
			sql += " and sid=?";
			params.add(sid);
		}
		
		if (!StringUtil.isNull(sname)){
			sql += " and sname like '%'||?||'%'";
			params.add(sname);
		}
		
		if (!StringUtil.isNull(cid)){
			sql += " and s.cid=?";
			params.add(cid);
		}
		
		if (StringUtil.isNull(cid) && !StringUtil.isNull(mid)){
			sql += " and c.mid=?";
			params.add(mid);
		}
		
		if (StringUtil.isNull(cid) && !StringUtil.isNull(grade)){
			sql += " and c.grade=?";
			params.add(grade);
		}
		sql += " order by sid) a where rownum<=?) where rn>?";
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.finds(sql,StuInfo.class, params);
	}

	@Override
	public int getTotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(sid) from stuInfo";
		return db.getTotal(sql);
	}

	@Override
	public int getTotal(String sid, String sname, String mid, String cid, String grade, int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql = "select count(sid) from stuInfo s,classInfo c,majorinfo m where s.cid=c.cid and c.mid=m.mid";
		
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(sid)){
			sql += " and sid=?";
			params.add(sid);
		}
		
		if (!StringUtil.isNull(sname)){
			sql += " and sname like '%'||?||'%'";
			params.add(sname);
		}
		
		if (!StringUtil.isNull(cid)){
			sql += " and s.cid=?";
			params.add(cid);
		}
		
		if (StringUtil.isNull(cid) && !StringUtil.isNull(mid)){
			sql += " and c.mid=?";
			params.add(mid);
		}
		
		if (StringUtil.isNull(cid) && !StringUtil.isNull(grade)){
			sql += " and c.grade=?";
			params.add(grade);
		}
		return db.getTotal(sql,params);
	}

	@Override
	public int resetPwd(String sid, String pwd) {
		DBHelper db = new DBHelper();
		String sql = "update stuInfo set pwd = ? where sid = ?";
		return db.update(sql, pwd, sid);
	}

	@Override
	public StuInfo findBySid(String sid) {
		DBHelper db = new DBHelper();
		String sql = "select s.*, cname, mname, s.cid, c.mid from stuInfo s,classInfo c,majorinfo m where s.cid=c.cid and c.mid=m.mid and sid = ?";
		return db.find(sql, StuInfo.class, sid);
	}

	@Override
	public int updateStuInfo(String sid, String sname, String cid, String sex, String cardId, String tel, String photo) {
		DBHelper db = new DBHelper();
		String sql = "update stuInfo set sname=?, cid=?, sex=?, cardId=?, tel=?, photo=? where sid=?";
		return db.update(sql, sname, cid, sex, cardId, tel, photo, sid);
	}

	@Override
	public int updatePhoto(String sid) {
		DBHelper db = new DBHelper();
		String sql = "update stuInfo set photo=sid||'.jpg' where sid=?";
 		return db.update(sql, sid);
	}

	@Override
	public StuInfo login(String account, String pwd) {
		DBHelper db = new DBHelper();
		String sql = "select sid, sname, pwd, cname,s.status, mname, sex, cardId, tel, photo from stuInfo s,classInfo c, majorInfo m where s.cid=c.cid and m.mid=c.mid and sid=? and pwd=?";
		return db.find(sql, StuInfo.class, account, pwd);
	}

	@Override
	public int updatePwd(String sid, String oldPwd, String newPwd) {
		DBHelper db = new DBHelper();
		String sql = "update stuInfo set pwd = ? where sid = ? and pwd = ?";
		return db.update(sql, newPwd, sid, oldPwd);
	}

	@Override
	public int deleteStu(String sid) {
		DBHelper db = new DBHelper();
		String sql = "delete from stuInfo where sid = ?";
		return db.update(sql, sid);
	}
}
