package com.yc.onlineexamsys.dao;

import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.Questions;


public interface IQuestionDao {
	/**
	 * 批量添加题目信息
	 * @param list
	 * @return
	 */
	public int addQuestions(List<List<String>> list);

	/**
	 * 单个添加题目信息
	 * @param qname 题目名
	 * @param tid 题目类型编号
	 * @param cid 所属课程编号
	 * @param ans1 选项A
	 * @param ans2 选项B
	 * @param ans3 选项C
	 * @param ans4 选项D
	 * @param ans 正确答案
	 * @return
	 */
	public int addQuestion(String qname, String tid, String cid, String ans1, String ans2, String ans3, String ans4, String ans);

	/**
	 * 分页查询题目信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Questions> findByPage(int pageNo, int pageSize);

	/**
	 * 根据题目类型、所属课程、题目名称组合查询
	 * @param tid 题目类型编号
	 * @param cid 题目所属课程编号
	 * @param qname 题目名称
	 * @return
	 */
	public List<Questions> findByCondition(String tid, String cid, String qname, int pageNo, int pageSize);

	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotal();

	/**
	 * 根据题目类型、所属课程、题目名称组合查询统计
	 * @param tid
	 * @param cid
	 * @param qname
	 * @return
	 */
	public int getTotal(String tid, String cid,String qname);
	
	/**
	 * 根据课程id获取该课程每种题型的题目数量
	 * @param cid
	 * @return
	 */
	public List<Map<String,String>> getTotals(String cid);

	/**
	 * 根据题目编号查看题目信息
	 * @param sid
	 * @return
	 */
	public Questions findByQid(String qid);

	/**
	 * 修改题目信息
	 * @param qid 题目编号
	 * @param qname 题目名称
	 * @param tid 题目类型
	 * @param cid 课程
	 * @param ans1
	 * @param ans2
	 * @param ans3
	 * @param ans4
	 * @param ans
	 * @return
	 */
	public int updateQuestion(String qid, String qname, String tid, String cid, String ans1, String ans2, String ans3, String ans4, String ans);

	/**
	 * 根据试题编号，查询所有试题信息
	 * @param qids
	 * @return
	 */
	public List<Questions> findByQids(String qids);
	
	/**
	 * 删除试题信息
	 * @param qid
	 * @return
	 */
	public int deleteQuestion(String qid);
	
	/**
	 * 判断该试题是否已经使用
	 * @param qid
	 * @return
	 */
	public boolean isUseQuestion(String qid);
}
