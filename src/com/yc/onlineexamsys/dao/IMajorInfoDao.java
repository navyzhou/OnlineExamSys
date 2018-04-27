package com.yc.onlineexamsys.dao;

import java.util.List;

import com.yc.onlineexamsys.bean.MajorInfo;

/**
 * 专业信息
 * @author Administrator
 *
 */
public interface IMajorInfoDao {
	/**
	 * 添加专业信息
	 * @param mname
	 * @return
	 */
	public int addMajorInfo(String mname);
	
	/**
	 * 修改专业信息
	 * @param mid
	 * @param mname
	 * @return
	 */
	public int updateMajorInfo(String mid,String mname);
	
	/**
	 * 修改专业状态
	 * @param mids
	 * @param status
	 * @return
	 */
	public int updateMajorInfo(String mids,int status);
	
	/**
	 * 查询所有专业
	 * @return
	 */
	public List<MajorInfo> findAll();
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<MajorInfo> findByPage(int pageNo,int pageSize);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotal();
}
