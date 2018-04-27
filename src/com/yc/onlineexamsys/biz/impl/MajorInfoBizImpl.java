package com.yc.onlineexamsys.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.MajorInfo;
import com.yc.onlineexamsys.biz.IMajorInfoBiz;
import com.yc.onlineexamsys.dao.IMajorInfoDao;
import com.yc.onlineexamsys.dao.impl.MajorInfoDaoImpl;
import com.yc.onlineexamsys.util.StringUtil;

public class MajorInfoBizImpl implements IMajorInfoBiz {

	@Override
	public int addMajorInfo(String mname) {
		if ( StringUtil.isNull(mname) ){
			return -1;
		} else {
			IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
			return majorInfoDao.addMajorInfo(mname);
		}
	}

	@Override
	public int updateMajorInfo(String mid, String mname) {
		if (StringUtil.isNull(mid, mname)){
			return -1;
		} else {
			IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
			return majorInfoDao.updateMajorInfo(mid, mname);
		}
	}

	@Override
	public int updateMajorInfo(String mids, int status) {
		if (StringUtil.isNull(mids)){
			return -1;
		} else {
			IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
			return majorInfoDao.updateMajorInfo(mids, status);
		}
	}

	@Override
	public List<MajorInfo> findAll() {
		IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
		return majorInfoDao.findAll();
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		IMajorInfoDao majorInfoDao = new MajorInfoDaoImpl();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", majorInfoDao.getTotal());
		map.put("rows", majorInfoDao.findByPage(pageNo, pageSize));
		return map;
	}

}
