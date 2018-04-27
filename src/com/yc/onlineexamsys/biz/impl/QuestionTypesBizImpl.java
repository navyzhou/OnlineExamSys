package com.yc.onlineexamsys.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.QuestionTypes;
import com.yc.onlineexamsys.biz.IQuestionTypesBiz;
import com.yc.onlineexamsys.dao.IQuestionTypesDao;
import com.yc.onlineexamsys.dao.impl.QuestionTypesDaoImpl;

public class QuestionTypesBizImpl implements IQuestionTypesBiz{

	@Override
	public List<QuestionTypes> findAll() {
		IQuestionTypesDao questionTypeDao = new QuestionTypesDaoImpl();
		return questionTypeDao.findAll();
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		IQuestionTypesDao questionTypeDao = new QuestionTypesDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", questionTypeDao.findByPage(pageNo, pageSize));
		map.put("total", questionTypeDao.getTotal());
		return map;
	}

}
