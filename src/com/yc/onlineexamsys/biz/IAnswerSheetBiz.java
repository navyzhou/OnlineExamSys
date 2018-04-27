package com.yc.onlineexamsys.biz;

import java.util.Map;

import com.yc.onlineexamsys.bean.AnswerSheet;

public interface IAnswerSheetBiz {
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
	 * 查询成绩
	 * @param sid
	 * @param pageNo
	 * @param pgeSize
	 * @return
	 */
	public Map<String, Object> findPageBySid(String sid, int pageNo, int pageSize);
	
	/**
	 * 查询成绩
	 * @param sid
	 * @param pageNo
	 * @param pgeSize
	 * @return
	 */
	public Map<String, Object> findByPage(int pageNo, int pageSize);
	
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
	public Map<String, Object> findByCondition(String majorId, String grade, String semester, String courseId, String classId,String sid, String sname, int pageNo, int pageSize);
	
	public AnswerSheet findByAid(String aid);
}
