package com.yc.onlineexamsys.biz.impl;

import java.util.HashMap;
import java.util.Map;

import com.yc.onlineexamsys.bean.AdminInfos;
import com.yc.onlineexamsys.biz.IAdminInfoBiz;
import com.yc.onlineexamsys.dao.IAdminInfoDao;
import com.yc.onlineexamsys.dao.impl.AdminInfoDaoImpl;
import com.yc.onlineexamsys.util.MD5Encryption;
import com.yc.onlineexamsys.util.StringUtil;

public class AdminInfoBizImpl implements IAdminInfoBiz {
	@Override
	public int add(String aname, String pwd, String email, String photo) {
		if (StringUtil.isNull(aname, pwd, email)) {
			return -1;
		} else {
			IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
			pwd = MD5Encryption.createPassword(pwd);
			int result = adminInfoDao.add(aname, pwd, email, photo);
			if (result>0) {
				return adminInfoDao.getAid(email);
			} else {
				return 0;
			}
		}
	}

	@Override
	public AdminInfos login(String account, String pwd) {
		if (StringUtil.isNull(account, pwd)) {
			return null;
		} else {
			IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
			pwd = MD5Encryption.createPassword(pwd);
			return adminInfoDao.login(account, pwd);
		}
	}

	@Override
	public Map<String, Object> findByPage(String aname, int pageNo, int pageSize) {
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		Map<String, Object> map =new  HashMap<String, Object>();
		map.put("total", adminInfoDao.getTotal(aname));
		map.put("rows", adminInfoDao.findByPage(aname, pageNo, pageSize));
		return map;
	}

	@Override
	public int updatePwd(String oldPwd, String newPwd, int id) {
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		oldPwd = MD5Encryption.createPassword(oldPwd);
		newPwd = MD5Encryption.createPassword(newPwd);
		return adminInfoDao.updatePwd(oldPwd, newPwd, id);
	}

	@Override
	public int updateChangeStatus(String aid, String status) {
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		return adminInfoDao.updateChangeStatus(aid, status);
	}

	@Override
	public int getCountByEmail(String aid, String email) {
		if (StringUtil.isNull(aid, email)) {
			return -1;
		} else {
			IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
			return adminInfoDao.getCountByEmail(aid, email);
		}
	}

	@Override
	public int updatePwdByEmail(String aid, String pwd) {
		if (StringUtil.isNull(aid, pwd)) {
			return -1;
		} else {
			IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
			pwd = MD5Encryption.createPassword(pwd);
			return adminInfoDao.updatePwdByEmail(aid, pwd);
		}
	}

	@Override
	public int updateHead(int aid, String photo) {
		IAdminInfoDao adminInfoDao = new AdminInfoDaoImpl();
		return adminInfoDao.updateHead(aid, photo);
	}
}
