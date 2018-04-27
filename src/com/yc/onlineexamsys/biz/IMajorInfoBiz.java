package com.yc.onlineexamsys.biz;

import java.util.List;
import java.util.Map;

import com.yc.onlineexamsys.bean.MajorInfo;

public interface IMajorInfoBiz {
	public int addMajorInfo(String mname);
	
	public int updateMajorInfo(String mid,String mname);
	
	public int updateMajorInfo(String mids,int status);
	
	public List<MajorInfo> findAll();
	
	public Map<String,Object> findByPage(int pageNo,int pageSize);
}
