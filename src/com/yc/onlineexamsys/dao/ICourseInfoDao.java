package com.yc.onlineexamsys.dao;

import java.util.List;

import com.yc.onlineexamsys.bean.CourseInfo;

public interface ICourseInfoDao {
	/**
	 * 添加课程信息
	 * @param cname
	 * @param semester
	 * @return
	 */
	public int addCourseInfo(String cname,String semester);
	
	/**
	 * 修改课程信息
	 * @param cid
	 * @param cname
	 * @param semester
	 * @return
	 */
	public int updateCourseInfo(String cid,String cname,String semester);
	
	/**
	 * 修改课程状态
	 * @param cids
	 * @param status
	 * @return
	 */
	public int updateCourseInfo(String cids,Integer status);
	
	/**
	 * 根据课程名称、学期或状态查询课程信息
	 * @param cname
	 * @param semester
	 * @param status
	 * @return
	 */
	public List<CourseInfo> find(String cname,String semester,Integer status);
	
	
	/**
	 * 查询所有课程信息
	 * @return
	 */
	public List<CourseInfo> finds();
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotal();
	
	/**
	 * 根据名称、学期或状态获取总记录数
	 * @param cname
	 * @param semester
	 * @return
	 */
	public int getTotal(String cname,String semester,Integer status);
	
	public List<CourseInfo> findByPage(int pageNo,int pageSize);
	
	public List<CourseInfo> findByPage(int pageNo,int pageSize,String cname,String semester,Integer status);
}
