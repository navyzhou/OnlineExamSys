package com.yc.onlineexamsys.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.ClassInfo;
import com.yc.onlineexamsys.biz.IClassInfoBiz;
import com.yc.onlineexamsys.dao.IClassInfoDao;
import com.yc.onlineexamsys.dao.impl.ClassInfoDaoImpl;
import com.yc.onlineexamsys.util.StringUtil;

public class ClassInfoBizImpl implements IClassInfoBiz {
	@Override
	public int addClassInfo(String cname, String mid, String grade, String length) {
		if (StringUtil.isNull(cname, mid, grade, length)){
			return -1;
		} else {
			IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
			return classInfoDao.addClassInfo(cname, mid, grade, length);
		}
	}

	@Override
	public int updateClassInfo(String cid, String cname, String mid, String grade, String length) {
		if (StringUtil.isNull(cid, cname, mid, grade, length )){
			return -1;
		} else {
			IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
			return classInfoDao.updateClassInfo(cid, cname, mid, grade, length);
		}
	}

	@Override
	public int updateClassInfo(String cids, int status) {
		if (StringUtil.isNull(cids)){
			return -1;
		} else {
			IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
			return classInfoDao.updateClassInfo(cids, status);
		}
	}

	@Override
	public List<ClassInfo> findAll() {
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		return classInfoDao.findAll();
	}

	@Override
	public Map<String,Object> findByPage(int pageNo, int pageSize) {
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", classInfoDao.findByPage(pageNo, pageSize));
		map.put("total", classInfoDao.getTotal());
		return map;
	}

	@Override
	public List<ClassInfo> find(String grade, String mid, String length) {
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		return classInfoDao.find(grade, mid, length);
	}

	@Override
	public Map<String,Object> find(String grade, String mid, String length, int pageNo, int pageSize) {
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total",classInfoDao.getTotal(grade, mid, length));
		map.put("rows",classInfoDao.find(grade, mid, length, pageNo, pageSize));
		return map;
	}

	@Override
	public List<ClassInfo> finds() {
		IClassInfoDao classInfoDao = new ClassInfoDaoImpl();
		return classInfoDao.finds();
	}
}
