package com.yc.onlineexamsys.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.CourseInfo;
import com.yc.onlineexamsys.biz.ICourseInfoBiz;
import com.yc.onlineexamsys.dao.ICourseInfoDao;
import com.yc.onlineexamsys.dao.impl.CourseInfoDaoImpl;
import com.yc.onlineexamsys.util.StringUtil;

public class CourseInfoBizImpl implements ICourseInfoBiz{

	@Override
	public int addCourseInfo(String cname, String semester) {
		if(StringUtil.isNull(cname,semester)){
			return -1;
		}else{
			ICourseInfoDao courseInfoDao=new CourseInfoDaoImpl();
			return courseInfoDao.addCourseInfo(cname, semester);
		}
	}

	@Override
	public int updateCourseInfo(String cid, String cname, String semester) {
		if(StringUtil.isNull(cid,cname,semester)){
			return -1;
		}else{
			ICourseInfoDao courseInfoDao=new CourseInfoDaoImpl();
			return courseInfoDao.updateCourseInfo(cid, cname, semester);
		}
	}

	@Override
	public int updateCourseInfo(String cids, Integer status) {
		ICourseInfoDao courseInfoDao=new CourseInfoDaoImpl();
		return courseInfoDao.updateCourseInfo(cids, status);
	}

	@Override
	public List<CourseInfo> finds() {
		ICourseInfoDao courseInfoDao=new CourseInfoDaoImpl();
		return courseInfoDao.finds();
	}

	@Override
	public Map<String, Object> findByPage(int pageNo,int pageSize) {
		ICourseInfoDao courseInfoDao=new CourseInfoDaoImpl();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("total",courseInfoDao.getTotal());
		map.put("rows",courseInfoDao.findByPage(pageNo, pageSize));
		return map;
	}

	@Override
	public Map<String, Object> findByPage(int pageNo,int pageSize,String cname, String semester, Integer status) {
		ICourseInfoDao courseInfoDao=new CourseInfoDaoImpl();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("total",courseInfoDao.getTotal(cname, semester, status));
		map.put("rows",courseInfoDao.findByPage(pageNo, pageSize, cname, semester, status));
		return map;
	}
}
