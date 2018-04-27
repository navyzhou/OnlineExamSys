package com.yc.onlineexamsys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.onlineexamsys.bean.CourseInfo;
import com.yc.onlineexamsys.dao.DBHelper;
import com.yc.onlineexamsys.dao.ICourseInfoDao;
import com.yc.onlineexamsys.util.StringUtil;

public class CourseInfoDaoImpl implements ICourseInfoDao{

	@Override
	public int addCourseInfo(String cname, String semester) {
		DBHelper db=new DBHelper();
		String sql="insert into courseInfo values(seq_courseInfo_cid.nextval,?,?,1)";
		return db.update(sql,cname,semester);
	}

	@Override
	public int updateCourseInfo(String cid, String cname, String semester) {
		DBHelper db=new DBHelper();
		String sql="update courseInfo set cname=?,semester=? where cid=?";
		return db.update(sql,cname,semester,cid);
	}

	@Override
	public int updateCourseInfo(String cids,Integer status){
		DBHelper db=new DBHelper();
		if(!StringUtil.isNull(cids) && !cids.contains(" or ")){
			String sql="update courseInfo set status=? where cid in("+cids+")";
			return db.update(sql,status);
		}else{
			String sql="update courseInfo set status=? where cid=?";
			return db.update(sql, status,cids);
		}
	}

	@Override
	public List<CourseInfo> finds() {
		DBHelper db=new DBHelper();
		String sql="select cid,cname,semester,status from courseInfo order by semester desc,cid desc";
		return db.finds(sql,CourseInfo.class);
	}
	
	@Override
	public List<CourseInfo> find(String cname, String semester, Integer status) {
		DBHelper db=new DBHelper();
		List<Object> params=new ArrayList<Object>();
		String sql="select cid,cname,semester,status from courseInfo where 1=1";
		if(!StringUtil.isNull(cname)){
			sql+=" and cname like '%'||?||'%'";
			params.add(cname);
		}
		
		if(!StringUtil.isNull(semester)){
			sql+=" and semester=?";
			params.add(semester);
		}
		
		if(status!=-1){
			sql+=" and status=?";
			params.add(status);
		}
		sql+=" order by semester desc,cid desc";
		return db.finds(sql,CourseInfo.class, params);
	}

	@Override
	public int getTotal() {
		DBHelper db=new DBHelper();
		String sql="select count(cid) from courseInfo";
		return db.getTotal(sql);
	}


	
	@Override
	public int getTotal(String cname, String semester,Integer status) {
		DBHelper db=new DBHelper();
		List<Object> params=new ArrayList<Object>();
		String sql="select count(cid) from courseInfo where 1=1";
		if(!StringUtil.isNull(cname)){
			sql+=" and cname like '%'||?||'%'";
			params.add(cname);
		}
		
		if(!StringUtil.isNull(semester)){
			sql+=" and semester=?";
			params.add(semester);
		}
		
		if(status!=-1){
			sql+=" and status=?";
			params.add(status);
		}
		return db.getTotal(sql, params);
	}

	@Override
	public List<CourseInfo> findByPage(int pageNo, int pageSize) {
		DBHelper db=new DBHelper();
		String sql="select * from (select a.*,rownum rn from(select cid,cname,semester,status from courseInfo order by semester desc,cid desc) a where rownum<=? ) where rn>?";
		return db.finds(sql,CourseInfo.class,pageNo*pageSize,(pageNo-1)*pageSize);
	}

	@Override
	public List<CourseInfo> findByPage(int pageNo, int pageSize, String cname, String semester, Integer status) {
		DBHelper db=new DBHelper();
		String sql="select * from (select a.*,rownum rn from(select cid,cname,semester,status from courseInfo where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(!StringUtil.isNull(cname)){
			sql+=" and cname like '%'||?||'%'";
			params.add(cname);
		}
		
		if(!StringUtil.isNull(semester)){
			sql+=" and semester=?";
			params.add(semester);
		}
		
		if(status!=-1){
			sql+=" and status=?";
			params.add(status);
		}
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		sql+=" order by semester desc,cid desc) a where rownum<=? ) where rn>?";
		
		return db.finds(sql,CourseInfo.class,params);
	}
}
