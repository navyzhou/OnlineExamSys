package com.yc.onlineexamsys.dao;

import java.util.List;

import com.yc.onlineexamsys.bean.AdminInfos;

public interface IAdminInfoDao {
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
	
	/**
	 * 分页查询
	 * @param aname
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<AdminInfos> findByPage(String aname, int pageNo, int pageSize);
	
	/**
	 * 获取总记录数
	 * @param aname
	 * @return
	 */
	public int getTotal(String aname);
	
	/**
	 * 根据邮箱获取管理员编号
	 * @param email
	 * @return
	 */
	public int getAid(String email);
}
