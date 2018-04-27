package com.yc.onlineexamsys.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.TestPaper;
import com.yc.onlineexamsys.dao.DBHelper;
import com.yc.onlineexamsys.dao.ITestPaperDao;
import com.yc.onlineexamsys.util.StringUtil;

public class TestPaperDaoImpl implements ITestPaperDao{

	@Override
	public List<Map<String, String>> findQuestionByCid(String cid) {
		DBHelper db = new DBHelper();
		String sql = "select qid||'-'||ans||'-'||tid qid, tid from questions where cid=?";
		return db.finds(sql, cid);
	}

	@Override
	public int addTestPaper(String pname, String cid, String testTime, String longExam, String cids, String subjects, String score) {
		DBHelper db = new DBHelper();
		String sql = "insert into testPaper values(seq_testPaper_pid.nextval,?,?,to_date(?,'yyyy-mm-dd HH24:mi'),?,?,?,?,1)";
		return db.update(sql, pname, cid, testTime, longExam, cids, subjects, score);
	}

	@Override
	public TestPaper findTestPaperByPid(String pid) {
		DBHelper db = new DBHelper();
		String sql = "select pid,pname,t.cid,cname,to_char(testTime,'yyyy-mm-dd hh24:mi') testTime,longExam,cids,subjects,score from testPaper t,courseInfo c where t.cid=c.cid and pid=?";
		return db.find(sql,TestPaper.class, pid);
	}

	@Override
	public List<TestPaper> findTestPaperByCid(String cid) {
		DBHelper db = new DBHelper();
		String sql = "select pid,pname,t.cid,to_char(testTime,'yyyy-mm-dd hh24:mi') testTime,cids,longExam,t.status,cname from testpaper t,courseInfo c "
				+ " where t.cid=c.cid and (cids=? or cids like ?||',%' or cids like '%,'||? or cids like '%,'||?||',%') "
				+ " and sysdate <= testTime and (t.status = 1 or t.status =2) order by testTime asc";
		return db.finds(sql,TestPaper.class, cid, cid, cid, cid);
	}

	@Override
	public List<TestPaper> findByPage(int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql = "select * from (select a.*, rownum rn from (select pid, pname, cid, to_char(testTime,'yyyy-mm-dd HH24:mi') testTime, longExam, cids, subjects, "
				+ "score, status from testPaper order by testTime desc, pid asc) a where rownum<=?) where rn>?";
		return db.finds(sql, TestPaper.class, pageNo*pageSize, (pageNo-1)*pageSize);
	}

	@Override
	public int getTotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(pid) from testPaper";
		return db.getTotal(sql);
	}

	@Override
	public int updateTestPaperStatus(String pid, String status) {
		DBHelper db = new DBHelper();
		String sql = "update testPaper set status = ? where pid = ?";
		return db.update(sql,status, pid);
	}

	@Override
	public int deleteTestPaper(String pid) {
		DBHelper db = new DBHelper();
		String sql = "delete from testPaper where pid = ?";
		return db.update(sql, pid);
	}

	@Override
	public List<TestPaper> findTestPaperBySidAndStatus(String cid, String sid) {
		DBHelper db = new DBHelper();
		String sql = "select pid,pname,t.cid,to_char(testTime,'yyyy-mm-dd hh24:mi') testTime,cids,longExam,t.status,cname from testpaper t,courseInfo c "
				+ " where t.cid=c.cid and (cids=? or cids like ?||',%' or cids like '%,'||? or cids like '%,'||?||',%') "
				+ " and sysdate <= testTime and (t.status = 1 or t.status =2) and pid not in(select pid from answerSheet where sid=?) order by testTime asc";
		return db.finds(sql,TestPaper.class, cid, cid, cid, cid, sid);
	}

	@Override
	public List<TestPaper> findByCondition(String semester, String courseId, String classId, String status, int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select * from (select a.*, rownum rn from (select pid, pname, t.cid, to_char(testTime,'yyyy-mm-dd HH24:mi') testTime, longExam, cids,score, t.status "
				+ "from testPaper t,courseInfo c where t.cid = c.cid  ";

		if (!StringUtil.isNull(courseId)) { // 如果课程不为空
			sql += " and t.cid = ?";
			params.add(courseId);
		}

		if (!StringUtil.isNull(semester)) { // 如果学期不为空
			sql += " and c.semester = ?";
			params.add(semester);
		}

		if (!StringUtil.isNull(status)) { // 如果学期不为空
			sql += " and t.status = ?";
			params.add(status);
		}

		if (!StringUtil.isNull(classId)) { // 如果学期不为空
			sql += " and instr(cids,?)>0";
			params.add(classId);
		}
		sql += " order by testTime desc, pid asc) a where rownum<=?) where rn>?";
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.finds(sql,TestPaper.class, params);
	}

	@Override
	public int getTotal(String semester, String courseId, String classId, String status) {
		DBHelper db = new DBHelper();
		String sql ="select count(pid) from testPaper t,courseInfo c where t.cid = c.cid";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(courseId)) { // 如果课程不为空
			sql += " and t.cid = ?";
			params.add(courseId);
		}

		if (!StringUtil.isNull(semester)) { // 如果学期不为空
			sql += " and c.semester = ?";
			params.add(semester);
		}

		if (!StringUtil.isNull(status)) { // 如果学期不为空
			sql += " and t.status = ?";
			params.add(status);
		}

		if (!StringUtil.isNull(classId)) { // 如果学期不为空
			sql += " and instr(cids,?)>0";
			params.add(classId);
		}
		return db.getTotal(sql, params);
	}
}
