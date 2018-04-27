package com.yc.onlineexamsys.biz;

import java.util.Map;

import com.yc.onlineexamsys.bean.AdminInfos;

public interface IAdminInfoBiz {
	/**
	 * 管理员注册
	 * @param rid
	 * @param aname
	 * @param pwd
	 * @param email
	 * @param photo
	 * @return
	 */
	public int add(String aname, String pwd, String email, String photo);
	
	/**
	 * 管理员登录
	 * @param account
	 * @param pwd
	 * @return
	 */
	public AdminInfos login(String account, String pwd);
	
	
	public Map<String, Object> findByPage(String aname, int pageNo, int pageSize);
}
