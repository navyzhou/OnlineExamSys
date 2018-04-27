package com.yc.onlineexamsys.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.Questions;
import com.yc.onlineexamsys.dao.DBHelper;
import com.yc.onlineexamsys.dao.IQuestionDao;
import com.yc.onlineexamsys.util.StringUtil;


public class QuestionDaoImpl implements IQuestionDao{

	@Override
	public int addQuestions(List<List<String>> list) {
		DBHelper db = new DBHelper();
		String sql = "insert into questions values(seq_questions_qid.nextval,?,?,?,?,?,?,?,?,1)";
		return db.updates(sql,list);
	}

	@Override
	public int addQuestion(String qname, String tid, String cid, String ans1,String ans2, String ans3, String ans4, String ans) {
		DBHelper db = new DBHelper();
		String sql = "insert into questions values(seq_questions_qid.nextval,?,?,?,?,?,?,?,?,1)";
		return db.update(sql, qname, tid, cid, ans1, ans2, ans3, ans4, ans);
	}

	@Override
	public List<Questions> findByPage(int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*, rownum rn from(select * from questions) a where rownum<=?) where rn>?";
		return db.finds(sql,Questions.class, pageNo*pageSize, (pageNo-1)*pageSize);
	}

	@Override
	public List<Questions> findByCondition(String tid, String cid, String qname, int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*, rownum rn from(select * from questions where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(tid)){
			sql += " and tid=?";
			params.add(tid);
		}
		
		if (!StringUtil.isNull(cid)){
			sql += " and cid=?";
			params.add(cid);
		}
		
		if (!StringUtil.isNull(qname)){
			sql += " and qname like '%'||?||'%'";
			params.add(qname);
		}
		
		sql += ") a where rownum<=?) where rn>?";
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.finds(sql, Questions.class, params);
	}

	@Override
	public int getTotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(qid) from questions";
		return db.getTotal(sql);
	}

	@Override
	public int getTotal(String tid, String cid, String qname) {
		DBHelper db = new DBHelper();
		String sql = "select count(qid) from questions where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isNull(tid)){
			sql += " and tid=?";
			params.add(tid);
		}
		
		if (!StringUtil.isNull(cid)){
			sql += " and cid=?";
			params.add(cid);
		}
		
		if (!StringUtil.isNull(qname)){
			sql += " and qname like '%'||?||'%'";
			params.add(qname);
		}
		return db.getTotal(sql,params);
	}

	@Override
	public List<Map<String, String>> getTotals(String cid) {
		DBHelper db = new DBHelper();
		String sql = "select count(qid) total, tid from questions where cid=? group by tid";
		return db.finds(sql,cid);
	}

	@Override
	public Questions findByQid(String qid) {
		DBHelper db = new DBHelper();
		String sql = "select * from questions where qid = ?";
		return db.find(sql, Questions.class, qid);
	}

	@Override
	public int updateQuestion(String qid, String qname, String tid, String cid, String ans1, String ans2, String ans3, String ans4, String ans) {
		DBHelper db = new DBHelper();
		String sql = "update questions set qname=?, tid=?, cid=?, ans1=?, ans2=?, ans3=?, ans4=?, ans=? where qid=?";
		return db.update(sql, qname, tid, cid, ans1, ans2, ans3, ans4, ans, qid);
	}

	@Override
	public List<Questions> findByQids(String qids) {
		DBHelper db = new DBHelper();
		String sql = "select * from questions where qid in("+qids+") order by tid asc";
		return db.finds(sql, Questions.class);
	}

	@Override
	public int deleteQuestion(String qid) {
		DBHelper db = new DBHelper();
		String sql = "delete from questions where qid=?";
		return db.update(sql,qid);
	}

	@Override
	public boolean isUseQuestion(String qid) {
		DBHelper db = new DBHelper();
		String sql = "select count(pid) from testPaper where subjects like ?||'-%' or subjects like '%,'||?||'-%'";
		if (db.getTotal(sql, qid, qid) >0) {
			return true;
		} else {
			return false;
		}
	}
}
