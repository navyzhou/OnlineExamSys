package com.yc.onlineexamsys.util;

public class StringUtil {
	public static boolean isNull(String ... strs){
		if(strs!=null && strs.length>0){
			for(String str:strs){
				if(str==null || "".equals(str)){
					return true;
				}
			}
			return false;
		}else{
			return true;
		}
	}
}	
