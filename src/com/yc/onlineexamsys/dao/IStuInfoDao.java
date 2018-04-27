package com.yc.onlineexamsys.dao;

import java.util.List;

import com.yc.onlineexamsys.bean.ClassInfo;
import com.yc.onlineexamsys.bean.StuInfo;

public interface IStuInfoDao {
	/**
	 * 获取专业、班级信息
	 * @return
	 */
	public List<ClassInfo> getInfo();

	/**
	 * 批量添加学生信息
	 * @param list
	 * @return
	 */
	public int addStuInfos(List<List<String>> list);

	/**
	 * 单个添加学生信息
	 * @param sid
	 * @param sname
	 * @param pwd
	 * @param cid
	 * @param sex
	 * @param photo
	 * @param cardId
	 * @param tel
	 * @return
	 */
	public int addStuInfo(String sid, String sname, String pwd, String cid, String sex, String photo, String cardId, String tel);

	/**
	 * 分页查询学生信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<StuInfo> findByPage(int pageNo, int pageSize);

	/**
	 * 多条件组合查询
	 * @param sid 学号
	 * @param sname 姓名
	 * @param mid 专业
	 * @param cid 班级
	 * @param grade 年级
	 * @param pageNo 
	 * @param pageSize
	 * @return
	 */
	public List<StuInfo> findByCondition(String sid, String sname,String mid, String cid, String grade, int pageNo, int pageSize);

	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotal();

	public int getTotal(String sid, String sname,String mid, String cid, String grade, int pageNo, int pageSize);

	/**
	 * 修改密码
	 * @param sid
	 * @param pwd
	 * @return
	 */
	public int resetPwd(String sid, String pwd);

	/**
	 * 根据学号查看学生信息
	 * @param sid
	 * @return
	 */
	public StuInfo findBySid(String sid);

	/**
	 * 修改学生信息
	 * @param sid
	 * @param sname
	 * @param cid
	 * @param sex
	 * @param cardId
	 * @param tel
	 * @return
	 */
	public int updateStuInfo(String sid, String sname, String cid, String sex, String cardId, String tel, String photo);

	/**
	 * 修改图片
	 * @param sid
	 * @return
	 */
	public int updatePhoto(String sid);
	
	/**
	 * 学生登录
	 * @param account
	 * @param pwd
	 * @return
	 */
	public StuInfo login(String account, String pwd);
}
