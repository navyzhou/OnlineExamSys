package com.yc.onlineexamsys.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.ClassInfo;
import com.yc.onlineexamsys.bean.StuInfo;
import com.yc.onlineexamsys.biz.IStuInfoBiz;
import com.yc.onlineexamsys.dao.IStuInfoDao;
import com.yc.onlineexamsys.dao.impl.StuInfoDaoImpl;
import com.yc.onlineexamsys.util.MD5Encryption;
import com.yc.onlineexamsys.util.StringUtil;

public class StuInfoBizImpl implements IStuInfoBiz{

	@Override
	public List<ClassInfo> getInfo() {
		IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
		return stuInfoDao.getInfo();
	}

	@Override
	public int addStuInfos(List<List<String>> list) {
		IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
		for (List<String> lt : list){
			lt.add(2, MD5Encryption.createPassword(lt.get(4).substring(12)));
			lt.add(5, lt.get(0)+".jpg");
		}
		return stuInfoDao.addStuInfos(list);
	}

	@Override
	public int addStuInfo(String sid, String sname, String cid, String sex, String cardId, String tel) {
		if (StringUtil.isNull(sid, cid, sname, cardId, tel)){
			return -1;
		}else {
			IStuInfoDao stuInfoDao = new StuInfoDaoImpl();
			String pwd = MD5Encryption.createPassword(cardId.substring(12));
			String photo = sid + ".jpg";
			return stuInfoDao.addStuInfo(sid, sname, pwd, cid, sex, photo, cardId, tel);
		}
	}

	@Override
	public Map<String, Object> findByPage(int pageNo, int pageSize) {
		IStuInfoDao stuInfoDao= new StuInfoDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", stuInfoDao.getTotal());
		map.put("rows", stuInfoDao.findByPage(pageNo, pageSize));
		return map;
	}

	@Override
	public Map<String, Object> findByCondition(String sid, String sname, String mid, String cid, String grade,int pageNo, int pageSize) {
		IStuInfoDao stuInfoDao= new StuInfoDaoImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", stuInfoDao.getTotal(sid, sname, mid, cid, grade, pageNo, pageSize));
		map.put("rows", stuInfoDao.findByCondition(sid, sname, mid, cid, grade, pageNo, pageSize));
		return map;
	}

	@Override
	public int resetPwd(String sid, String pwd) {
		if (StringUtil.isNull(sid, pwd)){
			return -1;
		} else {
			IStuInfoDao stuInfoDao= new StuInfoDaoImpl();
			pwd = MD5Encryption.createPassword(pwd);
			return stuInfoDao.resetPwd(sid, pwd);
		}
	}

	@Override
	public StuInfo findBySid(String sid) {
		IStuInfoDao stuInfoDao= new StuInfoDaoImpl();
		return stuInfoDao.findBySid(sid);
	}

	@Override
	public int updateStuInfo(String sid, String sname, String cid, String sex, String cardId, String tel,String photo) {
		if (StringUtil.isNull(sid, sname, cid, sex, cardId)){
			return -1;
		} else {
			IStuInfoDao stuInfoDao= new StuInfoDaoImpl();
			return stuInfoDao.updateStuInfo(sid, sname, cid, sex, cardId, tel, photo);
		}
	}

	@Override
	public int updatePhoto(String sid) {
		if (StringUtil.isNull(sid)){
			return -1;
		} else {
			IStuInfoDao stuInfoDao= new StuInfoDaoImpl();
			return stuInfoDao.updatePhoto(sid);
		}
	}

	@Override
	public StuInfo login(String account, String pwd) {
		if (StringUtil.isNull(account, pwd)){
			return null;
		} else {
			pwd = MD5Encryption.createPassword(pwd);
			IStuInfoDao stuInfoDao= new StuInfoDaoImpl();
			return stuInfoDao.login(account, pwd);
		}
	}
}
