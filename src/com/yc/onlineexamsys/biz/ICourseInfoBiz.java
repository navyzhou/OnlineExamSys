package com.yc.onlineexamsys.biz;

import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.CourseInfo;

public interface ICourseInfoBiz {
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
	 * 查询所有课程信息
	 * @return
	 */
	public List<CourseInfo> finds();
	
	/**
	 * 以easyui的格式返回所有课程信息
	 * @return
	 */
	public Map<String,Object> findByPage(int pageNo,int pageSize);
	
	/**
	 * 以easyui的格式，按条件返回所有课程信息
	 * @return
	 */
	public Map<String,Object> findByPage(int pageNo,int pageSize,String cname,String semester,Integer status);
}
