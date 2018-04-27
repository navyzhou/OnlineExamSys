package com.yc.onlineexamsys.biz.impl;

import java.util.HashMap;
import java.util.Map;

import com.yc.onlineexamsys.bean.AnswerSheet;
import com.yc.onlineexamsys.biz.IAnswerSheetBiz;
import com.yc.onlineexamsys.dao.IAnswerSheetDao;
import com.yc.onlineexamsys.dao.impl.AnswerSheetDaoImpl;
import com.yc.onlineexamsys.util.StringUtil;

public class AnswerSheetBizImpl implements IAnswerSheetBiz{

	@Override
	public int add(String pid, String sid, String ans, int score, int surplus) {
		if (StringUtil.isNull(pid, sid, ans)){
			return -1;
		} else {
			IAnswerSheetDao dao = new AnswerSheetDaoImpl();
			return dao.add(pid, sid, ans, score, surplus);
		}
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		IAnswerSheetDao dao = new AnswerSheetDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", dao.getTotal());
		map.put("rows", dao.findByPage(pageNo, pageSize));
		return map;
	}


	@Override
	public Map<String, Object> findPageBySid(String sid, int pageNo, int pageSize) {
		if (StringUtil.isNull(sid)){
			return null;
		} else {
			IAnswerSheetDao dao = new AnswerSheetDaoImpl();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", dao.getTotal(sid));
			map.put("rows", dao.findPageBySid(sid, pageNo, pageSize));
			return map;
		}
	}

	@Override
	public Map<String, Object> findByCondition(String majorId, String grade, String semester, String courseId, String classId, String sid, String sname, int pageNo, int pageSize) {
		IAnswerSheetDao dao = new AnswerSheetDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", dao.getTotal(majorId, grade, semester, courseId, classId, sid, sname));
		map.put("rows", dao.findByCondition(majorId, grade, semester, courseId, classId, sid, sname, pageNo, pageSize));
		return map;
	}
	
	public AnswerSheet findByAid(String aid){
		if (StringUtil.isNull(aid)){
			return null;
		} else {
			IAnswerSheetDao dao = new AnswerSheetDaoImpl();
			return dao.findByAid(aid);
		}
	}
}
