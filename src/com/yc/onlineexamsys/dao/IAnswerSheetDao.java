package com.yc.onlineexamsys.dao;

import java.util.List;

import com.yc.onlineexamsys.bean.AnswerSheet;

public interface IAnswerSheetDao {
	/**
	 * 交卷
	 * @param pid 试卷编号
	 * @param sid 学号
	 * @param ans 答案
	 * @param score 分数
	 * @param surplus 剩余时长
	 * @return
	 */
	public int add(String pid, String sid, String ans, int score, int surplus);
	
	/**
	 * 根据学号查询学生的成绩
	 * @param sid
	 * @return
	 */
	public List<AnswerSheet> findPageBySid(String sid, int pageNo, int pageSize);
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pgeSize
	 * @return
	 */
	public List<AnswerSheet> findByPage(int pageNo, int pageSize);
	
	
	/**
	 * 多条件组合查询
	 * @param majorId 专业
	 * @param grade 年级
	 * @param semester 学期
	 * @param courseId 课程
	 * @param classId 班级
	 * @param sid 学号
	 * @param sname 姓名
	 * @param pageNo
	 * @param pgeSize
	 * @return
	 */
	public List<AnswerSheet> findByCondition(String majorId, String grade, String semester, String courseId, String classId,String sid, String sname, int pageNo, int pageSize);
	
	
	public int getTotal(String majorId, String grade, String semester, String courseId, String classId,String sid, String sname);
	
	/**
	 * 统计所有
	 * @return
	 */
	public int getTotal();
	
	/**
	 * 根据学号统计
	 * @param sid
	 * @return
	 */
	public int getTotal(String sid);
	
	/**
	 * 查询答卷
	 * @param aid
	 * @return
	 */
	public AnswerSheet findByAid(String aid);
}
