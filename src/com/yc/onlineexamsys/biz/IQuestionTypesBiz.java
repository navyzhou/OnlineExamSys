package com.yc.onlineexamsys.biz;

import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.QuestionTypes;

/**
 * 题目类型
 * @author navy
 *
 */
public interface IQuestionTypesBiz {
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
	public Map<String, Object> findByPage(int pageNo,int pageSize);
}
