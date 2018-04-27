package com.yc.onlineexamsys.biz;

import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.TestPaper;

public interface ITestPaperBiz {
	/**
	 * 获取专业、班级和课程信息
	 * @return
	 */
	public Map<String, Object> getInfos();
	
	/**
	 * 根据课程编号获取该课程的所有题目的题号和正确答案以及题型
	 * @param cid
	 * @return
	 */
	public List<Map<String, String>> findQuestionByCid(String cid);
	
	/**
	 * 添加试卷信息
	 * @param pname
	 * @param cid
	 * @param testTime
	 * @param longExam
	 * @param cids
	 * @param subjects
	 * @param score
	 * @return
	 */
	public int addTestPaper(String pname, String cid, String testTime, String longExam, String cids, String subjects, String score);
	
	/**
	 * 根据试卷编号查询试卷信息
	 * @param pid
	 * @return
	 */
	public TestPaper findTestPaperByPid(String pid);
	
	/**
	 * 根据班级编号，查询这个班级的还未开考的信息
	 * @param cid 班级编号
	 * @return
	 */
	public List<TestPaper> findTestPaperByCid(String cid);
	
	/**
	 * 多条件组合查询
	 * @param majorId 专业
	 * @param grade 年级
	 * @param semester 学期
	 * @param courseId 课程
	 * @param classId 班级
	 * @param status 状态
	 * @param pageNo
	 * @param pgeSize
	 * @return
	 */
	public Map<String, Object> findByCondition(String semester, String courseId, String classId,String status, int pageNo, int pageSize);
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> findByPage(int pageNo, int pageSize);
	
	/**
	 * 修改试卷状态
	 * @param pid
	 * @param status
	 * @return
	 */
	public int updateTestPaperStatus(String pid, String status);
	
	/**
	 * 删除试卷
	 * @param pid
	 * @return
	 */
	public int deleteTestPaper(String pid);
	
	public List<TestPaper> findTestPaperBySidAndStatus(String cid, String sid);
}
