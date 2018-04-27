package com.yc.onlineexamsys.dao.impl;

import java.util.List;

import com.yc.onlineexamsys.bean.QuestionTypes;
import com.yc.onlineexamsys.dao.DBHelper;
import com.yc.onlineexamsys.dao.IQuestionTypesDao;

public class QuestionTypesDaoImpl implements IQuestionTypesDao{

	@Override
	public List<QuestionTypes> findAll() {
		DBHelper db = new DBHelper();
		String sql = "select tid, tname from questionTypes order by tid";
		return db.finds(sql, QuestionTypes.class);
	}

	@Override
	public List<QuestionTypes> findByPage(int pageNo, int pageSize) {
		DBHelper db = new DBHelper();
		String sql = "select * from(select a.*, rownum rn from(select tid, tname from questionTypes order by tid) a where rownum <=?) where rn>?";
		return db.finds(sql, QuestionTypes.class, pageNo*pageSize, (pageNo-1)*pageSize );
	}

	@Override
	public int getTotal() {
		DBHelper db = new DBHelper();
		String sql = "select count(tid) from questionTypes";
		return db.getTotal(sql);
	}

}
