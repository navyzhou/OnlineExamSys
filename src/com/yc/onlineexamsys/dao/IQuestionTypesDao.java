package com.yc.onlineexamsys.dao;

import java.util.List;

import com.yc.onlineexamsys.bean.QuestionTypes;

/**
 * 题目类型
 * @author navy
 *
 */
public interface IQuestionTypesDao {
	/**
	 * 查询所有题目类型
	 * @return
	 */
	public List<QuestionTypes> findAll();
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<QuestionTypes> findByPage(int pageNo,int pageSize);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotal();
}
