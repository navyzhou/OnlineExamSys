package com.yc.onlineexamsys.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.CourseInfo;
import com.yc.onlineexamsys.bean.QuestionTypes;
import com.yc.onlineexamsys.bean.Questions;
import com.yc.onlineexamsys.biz.ICourseInfoBiz;
import com.yc.onlineexamsys.biz.IQuestionBiz;
import com.yc.onlineexamsys.biz.IQuestionTypesBiz;
import com.yc.onlineexamsys.dao.IQuestionDao;
import com.yc.onlineexamsys.dao.impl.QuestionDaoImpl;
import com.yc.onlineexamsys.util.StringUtil;

public class QuestionBizImpl implements IQuestionBiz{

	@Override
	public int addQuestions(List<List<String>> list) {
		if (list == null || list.isEmpty()) {
			return -1;
		} else {
			IQuestionDao questionDao = new QuestionDaoImpl();
			return questionDao.addQuestions(list);
		}
	}

	@Override
	public int addQuestion(String qname, String tid, String cid, String ans1, String ans2, String ans3, String ans4, String ans) {
		if (StringUtil.isNull(qname, tid, cid, ans)) {
			return -1;
		} else {
			if ("3".equals(tid) || "4".equals(tid)) {
				ans1="";
				ans2="";
				ans3="";
				ans4="";
			} else {
				if(StringUtil.isNull(ans1, ans2, ans3, ans4)){
					return -1;
				}
			}
			IQuestionDao questionDao = new QuestionDaoImpl();
			return questionDao.addQuestion(qname, tid, cid, ans1, ans2, ans3, ans4, ans);
		}
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		IQuestionDao questionDao = new QuestionDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", questionDao.findByPage(pageNo, pageSize));
		map.put("total", questionDao.getTotal());
		return map;
	}

	@Override
	public Map<String, Object> findByCondition(String tid, String cid, String qname, int pageNo, int pageSize) {
		IQuestionDao questionDao = new QuestionDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", questionDao.findByCondition(tid, cid, qname, pageNo, pageSize));
		map.put("total", questionDao.getTotal(tid, cid, qname));
		return map;
	}

	@Override
	public List<Map<String, String>> getTotals(String cid) {
		IQuestionDao questionDao = new QuestionDaoImpl();
		return questionDao.getTotals(cid);
	}

	@Override
	public Questions findByQid(String qid) {
		if (StringUtil.isNull(qid)){
			return null;
		} else {
			IQuestionDao questionDao = new QuestionDaoImpl();
			return questionDao.findByQid(qid);
		}
	}

	@Override
	public int updateQuestion(String qid, String qname, String tid, String cid, String ans1, String ans2, String ans3, String ans4, String ans) {
		if (StringUtil.isNull(qname, tid, cid, ans)){
			return -1;
		} else {
			if ("3".equals(tid) || "4".equals(tid)) {
				ans1="";
				ans2="";
				ans3="";
				ans4="";
			} else {
				if(StringUtil.isNull(ans1, ans2, ans3, ans4)){
					return -1;
				}
			}
			IQuestionDao questionDao = new QuestionDaoImpl();
			return questionDao.updateQuestion(qid, qname, tid, cid, ans1, ans2, ans3, ans4, ans);
		}
	}

	@Override
	public List<Questions> findByQids(String qids) {
		IQuestionDao questionDao = new QuestionDaoImpl();
		return questionDao.findByQids(qids);
	}

	@Override
	public Map<String, Object> getInfos() {
		Map<String, Object> map = new HashMap<String,Object>();
		ICourseInfoBiz courseInfoBiz = new  CourseInfoBizImpl();
		List<CourseInfo> cfs = courseInfoBiz.finds();

		IQuestionTypesBiz questionTypeBiz = new QuestionTypesBizImpl();
		List<QuestionTypes> qs = questionTypeBiz.findAll();

		map.put("courseInfos", cfs);
		map.put("questionTypes", qs);
		return map;
	}

	@Override
	public int deleteQuestion(String qid) {
		if (StringUtil.isNull(qid)){
			return -2;
		} else { 
			IQuestionDao questionDao = new QuestionDaoImpl();
			if (questionDao.isUseQuestion(qid)){
				return -1;
			} else {
				return questionDao.deleteQuestion(qid);
			}
		}
	}
}
