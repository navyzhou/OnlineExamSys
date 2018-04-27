package com.yc.onlineexamsys.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.ClassInfo;
import com.yc.onlineexamsys.bean.CourseInfo;
import com.yc.onlineexamsys.bean.MajorInfo;
import com.yc.onlineexamsys.bean.TestPaper;
import com.yc.onlineexamsys.biz.ITestPaperBiz;
import com.yc.onlineexamsys.dao.IClassInfoDao;
import com.yc.onlineexamsys.dao.ICourseInfoDao;
import com.yc.onlineexamsys.dao.IMajorInfoDao;
import com.yc.onlineexamsys.dao.ITestPaperDao;
import com.yc.onlineexamsys.dao.impl.ClassInfoDaoImpl;
import com.yc.onlineexamsys.dao.impl.CourseInfoDaoImpl;
import com.yc.onlineexamsys.dao.impl.MajorInfoDaoImpl;
import com.yc.onlineexamsys.dao.impl.TestPaperDaoImpl;
import com.yc.onlineexamsys.util.StringUtil;

public class TestPaperBizImpl implements ITestPaperBiz{

	@Override
	public Map<String, Object> getInfos() {
		// 获取专业信息
		IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
		List<MajorInfo> majors = majorInfoDao.findAll();
		
		// 获取班级信息
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		List<ClassInfo> classInfos = classInfoDao.finds();
		
		// 课程信息
		ICourseInfoDao courseInfoDao = new  CourseInfoDaoImpl();
		List<CourseInfo> courseInfos = courseInfoDao.finds();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("majors", majors);
		map.put("classInfos", classInfos);
		map.put("courseInfos", courseInfos);
		return map;
	}

	@Override
	public List<Map<String, String>> findQuestionByCid(String cid) {
		if (StringUtil.isNull(cid)) {
			return null;
		} else {
			ITestPaperDao testPaperDao = new TestPaperDaoImpl();
			return testPaperDao.findQuestionByCid(cid);
		}
	}

	@Override
	public int addTestPaper(String pname, String cid, String testTime, String longExam, String cids, String subjects, String score) {
		if (StringUtil.isNull(pname, cid, testTime, longExam, cids, subjects, score)) {
			return -1;
		} else {
			ITestPaperDao testPaperDao = new TestPaperDaoImpl();
			return testPaperDao.addTestPaper(pname, cid, testTime, longExam, cids, subjects, score);
		}
	}

	@Override
	public TestPaper findTestPaperByPid(String pid) {
		if (StringUtil.isNull(pid)) {
			return null;
		} else {
			ITestPaperDao testPaperDao = new TestPaperDaoImpl();
			return testPaperDao.findTestPaperByPid(pid);
		}
	}

	@Override
	public List<TestPaper> findTestPaperByCid(String cid) {
		if (StringUtil.isNull(cid)) {
			return null;
		} else {
			ITestPaperDao testPaperDao = new TestPaperDaoImpl();
			return testPaperDao.findTestPaperByCid(cid);
		}
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		ITestPaperDao testPaperDao = new TestPaperDaoImpl();
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("total", testPaperDao.getTotal());
		map.put("rows", testPaperDao.findByPage(pageNo, pageSize));
		return map;
	}

	@Override
	public int updateTestPaperStatus(String pid, String status) {
		if (StringUtil.isNull(pid,status)) {
			return -1;
		} else {
			ITestPaperDao testPaperDao = new TestPaperDaoImpl();
			return testPaperDao.updateTestPaperStatus(pid, status);
		}
	}

	@Override
	public int deleteTestPaper(String pid) {
		if (StringUtil.isNull(pid)) {
			return -1;
		} else {
			ITestPaperDao testPaperDao = new TestPaperDaoImpl();
			return testPaperDao.deleteTestPaper(pid);
		}
	}
	
	public List<TestPaper> findTestPaperBySidAndStatus(String cid, String sid){
		if (StringUtil.isNull(cid, sid)) {
			return null;
		} else {
			ITestPaperDao testPaperDao = new TestPaperDaoImpl();
			return testPaperDao.findTestPaperBySidAndStatus(cid, sid);
		}
	}

	@Override
	public Map<String, Object> findByCondition(String semester, String courseId, String classId, String status, int pageNo, int pageSize) {
		ITestPaperDao testPaperDao = new TestPaperDaoImpl();
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("total", testPaperDao.getTotal(semester, courseId, classId, status));
		map.put("rows", testPaperDao.findByCondition(semester, courseId, classId, status, pageNo, pageSize));
		return map;
	}
}
