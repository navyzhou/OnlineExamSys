package com.yc.onlineexamsys.dao;

import java.util.List;

import com.yc.onlineexamsys.bean.ClassInfo;

/**
 * 班级信息
 * @author Administrator
 *
 */
public interface IClassInfoDao {
	/**
	 * 添加班级信息
	 * @param cname
	 * @param mid
	 * @param grade
	 * @param length
	 * @return
	 */
	public int addClassInfo(String cname, String mid, String grade, String length);
	
	/**
	 * 修改班级信息
	 * @param mid
	 * @param cname
	 * @return
	 */
	public int updateClassInfo(String cid, String cname, String mid, String grade, String length);
	
	/**
	 * 修改班级状态
	 * @param cids
	 * @param status
	 * @return
	 */
	public int updateClassInfo(String cids, int status);
	
	/**
	 * 查询所有班级
	 * @return
	 */
	public List<ClassInfo> findAll();
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<ClassInfo> findByPage(int pageNo, int pageSize);
	
	/**
	 * 多件查询班级信息
	 * @param grade
	 * @param mid
	 * @param length
	 * @return
	 */
	public List<ClassInfo> find(String grade, String mid, String length);
	
	/**
	 * 多条件组合分页查询班级信息
	 * @param grade
	 * @param mid
	 * @param length
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<ClassInfo> find(String grade, String mid, String length, int pageNo, int pageSize);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotal();

	/**
	 * 多条件统计班级数量
	 * @param grade
	 * @param mid
	 * @param length
	 * @return
	 */
	public int getTotal(String grade, String mid, String length);
	
	public List<ClassInfo> finds();
}
