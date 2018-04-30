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
	
	/**
	 * 修改密码
	 * @param oldPwd
	 * @param newPwd
	 * @param id
	 * @return
	 */
	public int updatePwd(String oldPwd,String newPwd,int id);
	
	public int updateChangeStatus(String aid, String status);
	
	/**
	 * 根据账号和邮箱查询用户
	 * @param aid
	 * @param email
	 * @return
	 */
	public int getCountByEmail(String aid, String email);
	
	/**
	 * 重置密码
	 * @param aid
	 * @param pwd
	 * @return
	 */
	public int updatePwdByEmail(String aid, String pwd);
}
