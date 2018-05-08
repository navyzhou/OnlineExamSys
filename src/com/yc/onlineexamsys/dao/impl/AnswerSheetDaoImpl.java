package com.yc.onlineexamsys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.onlineexamsys.bean.AnswerSheet;
import com.yc.onlineexamsys.dao.DBHelper;
import com.yc.onlineexamsys.dao.IAnswerSheetDao;
import com.yc.onlineexamsys.util.StringUtil;

public class AnswerSheetDaoImpl implements IAnswerSheetDao{

	@Override
	public int add(String pid, String sid, String ans, int score, int surplus) {
		DBHelper db = new DBHelper();
		String sql = "insert into answerSheet values(seq_testPaper_aid.nextval,?,?,?,?,?,1)";
		return db.update(sql, pid, sid, ans, score, surplus);
	}

	@Override
	public List<AnswerSheet> findPageBySid(String sid, int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql="select * from (select aa.*,rownum rn from("
				+ "	select s.sid,p.pid,c.cname pname,c.semester sname, a.score,p.status from stuInfo s join classInfo cl on s.cid=cl.cid "
				+ "join testpaper p on p.cids like cl.cid||',%' or  p.cids like '%,'||cl.cid or p.cids like '%,'||cl.cid||',%' or p.cids=to_char(cl.cid) "
				+ "join courseinfo c on c.cid=p.cid left join answersheet a on a.sid=s.sid and p.pid=a.pid where p.status>2 and s.sid =?) aa where rownum<=?) where rn>?";
		return db.finds(sql, AnswerSheet.class, sid, pageNo*pageSize, (pageNo-1)*pageSize);
	}

	@Override
	public int getTotal(String sid) {
		DBHelper db = new DBHelper();
		String sql = "select count(s.sid) from stuInfo s join classInfo cl on s.cid=cl.cid "
				+ "join testpaper p on p.cids like cl.cid||',%' or  p.cids like '%,'||cl.cid or p.cids like '%,'||cl.cid||',%' or p.cids=to_char(cl.cid) "
				+ "join courseinfo c on c.cid=p.cid left join answersheet a on a.sid=s.sid and p.pid=a.pid where p.status>=2 and s.sid =?";
		return db.getTotal(sql, sid);
	}

	@Override
	public List<AnswerSheet> findByPage(int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql="select * from (select aa.*,rownum rn from("
				+ "	select s.sid,s.sname,p.pid,c.cname pname,c.cname ans,a.score,c.semester surplus,p.status from stuInfo s join classInfo cl on s.cid=cl.cid "
				+ "join testpaper p on p.cids like cl.cid||',%' or  p.cids like '%,'||cl.cid or p.cids like '%,'||cl.cid||',%' or p.cids=to_char(cl.cid) "
				+ "join courseinfo c on c.cid=p.cid left join answersheet a on a.sid=s.sid and p.pid=a.pid where p.status>=2 order by sid) aa where rownum<=?) where rn>?";
		return db.finds(sql, AnswerSheet.class,pageNo*pageSize, (pageNo-1)*pageSize);
	}

	@Override
	public List<AnswerSheet> findByCondition(String majorId, String grade, String semester, String courseId, String classId, String sid, String sname, int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql="select * from (select aa.*,rownum rn from("
				+ "	select s.sid,s.sname,p.pid,c.cname pname,c.cname ans,a.score,c.semester surplus,p.status from stuInfo s join classInfo cl on s.cid=cl.cid "
				+ "join testpaper p on p.cids like cl.cid||',%' or  p.cids like '%,'||cl.cid or p.cids like '%,'||cl.cid||',%' or p.cids=to_char(cl.cid) "
				+ "join courseinfo c on c.cid=p.cid left join answersheet a on a.sid=s.sid and p.pid=a.pid where p.status>=2";
		
		List<Object> list = new ArrayList<Object>();
		
		if (!StringUtil.isNull(sid)){ // 学号不为空
			sql += " and s.sid = ?";
			list.add(sid);
		} else {
			if (!StringUtil.isNull(sname)){ // 姓名不为空
				sql += " and s.sname like '%'||?||'%'";
				list.add(sname);
			}
			
			if (!StringUtil.isNull(classId)){ // 班级不为空
				sql += " and cl.cid=?";
				list.add(classId);
			}
			
			if (!StringUtil.isNull(courseId)){ // 课程不为空
				sql += " and p.cid=?";
				list.add(courseId);
			}
			
			if (!StringUtil.isNull(semester)){ // 学期
				sql += " and semester=?";
				list.add(semester);
			}
			
			if (!StringUtil.isNull(grade)){ // 年级
				sql += " and cl.grade=?";
				list.add(grade);
			}
			
			if (!StringUtil.isNull(majorId)){ // 专业
				sql += " and cl.mid=?";
				list.add(majorId);
			}
			 
		}
		sql += " order by sid) aa where rownum<=?) where rn>?";
		list.add(pageNo*pageSize);
		list.add((pageNo-1)*pageSize);
		
		return db.finds(sql, AnswerSheet.class, list);
	}

	@Override
	public int getTotal(String majorId, String grade, String semester, String courseId, String classId, String sid, String sname) {
		DBHelper db = new DBHelper();
		String sql="select count(s.sid) from stuInfo s join classInfo cl on s.cid=cl.cid "
				+ "join testpaper p on p.cids like cl.cid||',%' or  p.cids like '%,'||cl.cid or p.cids like '%,'||cl.cid||',%' or p.cids=to_char(cl.cid) "
				+ "join courseinfo c on c.cid=p.cid left join answersheet a on a.sid=s.sid and p.pid=a.pid where p.status>=2";
		List<Object> list = new ArrayList<Object>();
		
		if (!StringUtil.isNull(sid)){ // 学号不为空
			sql += " and s.sid=?";
			list.add(sid);
		} else {
			if (!StringUtil.isNull(sname)){ // 姓名不为空
				sql += " and s.sname like '%'||?||'%'";
				list.add(sname);
			}
			
			if (!StringUtil.isNull(classId)){ // 班级不为空
				sql += " and cl.cid=?";
				list.add(classId);
			}
			
			if (!StringUtil.isNull(courseId)){ // 课程不为空
				sql += " and p.cid=?";
				list.add(courseId);
			}
			
			if (!StringUtil.isNull(semester)){ // 学期
				sql += " and semester=?";
				list.add(semester);
			}
			
			if (!StringUtil.isNull(grade)){ // 年级
				sql += " and cl.grade=?";
				list.add(grade);
			}
			
			if (!StringUtil.isNull(majorId)){ // 专业
				sql += " and cl.mid=?";
				list.add(majorId);
			}
		}
		return db.getTotal(sql, list);
	}

	@Override
	public int getTotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(s.sid) from stuInfo s join classInfo cl on s.cid=cl.cid "
				+ "join testpaper p on p.cids like cl.cid||',%' or  p.cids like '%,'||cl.cid or p.cids like '%,'||cl.cid||',%' or p.cids=to_char(cl.cid) "
				+ "join courseinfo c on c.cid=p.cid left join answersheet a on a.sid=s.sid and p.pid=a.pid where p.status>=2";
		return db.getTotal(sql);
	}

	@Override
	public AnswerSheet findByAid(String aid) {
		DBHelper db = new DBHelper();
		String sql = "select a.aid,cname pname,ans,a.score from answersheet a,testpaper p,courseinfo c where a.pid=p.pid and p.cid=c.cid and a.aid=?";
		return db.find(sql, AnswerSheet.class, aid);
	}
}
