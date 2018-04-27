package com.yc.onlineexamsys.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class FileUploadUtil {
	public static String PATH="files"; //文件上传后存储在服务器端的哪个路径下
	public static String PHOTOPATH="photos"; //文件上传后存储在服务器端的哪个路径下
	private static final String ALLOWED="gif,jpg,png,jpeg,doc,txt,xls,xlsx"; //允许上传的文件列表
	private static final String DENIED="exe,bat,jsp,html"; //拒绝上传的文件列表
	private static final int SINGLEFILESIZE=1024*1024*10; //每个文件的最大大小
	private static final int TOTALMAXSIZE=100*1024*1024; //每次上传的最大大小
	
	public Map<String,String> upload(PageContext pageContext){
		Map<String,String> map=new HashMap<String,String>();
		SmartUpload su=new SmartUpload();
		
		try {
			//初始化上传工具 
			su.initialize(pageContext);
			
			//设置参数
			su.setCharset("utf-8");
			su.setAllowedFilesList( ALLOWED );
			su.setDeniedFilesList(DENIED);
			su.setMaxFileSize(SINGLEFILESIZE);
			su.setTotalMaxFileSize(TOTALMAXSIZE);
			
			//开始上传
			su.upload();
			
			//从smartupload请求中获取普通文本参数  text、number、password 即非  file
			Request request=su.getRequest();

			//从请求中获取所有普通文本框的name属性的属性值
			Enumeration enums=request.getParameterNames();
			String fieldName=null;
			while(enums.hasMoreElements()){
				fieldName=String.valueOf(enums.nextElement());
				map.put(fieldName, request.getParameter(fieldName));
			}
			
			if(su.getFiles()!=null && su.getFiles().getCount()>0){ //说明有文件要上传
				Files fs=su.getFiles();
				Collection<File> files=fs.getCollection();
				String path=null;
				String filePath="";
				String fName="";
				String temp=null;
				for(File fl:files){ //循环所有的文件
					if(!fl.isMissing()){
						fName=fl.getFieldName();

						if(temp==null){
							temp=fName;
						}else{
							if(!temp.equals(fName)){
								map.put(temp,filePath);
								filePath="";
								temp=fName;
							}
						}
						
						path="../adminPhotos/"+new Date().getTime()+"_"+fl.getFileName();
						//上传文件
						fl.saveAs(pageContext.getServletContext().getRealPath("/")+path);
						if(filePath!=""){
							filePath+=",";
						}
						filePath+=path;
					}
				}
				map.put(fName,filePath);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	public String uploadExcelFile(PageContext pageContext){
		SmartUpload su=new SmartUpload();
		
		String filePath="";
		try {
			//初始化上传工具 
			su.initialize(pageContext);
			
			//设置参数
			su.setCharset("utf-8");
			su.setAllowedFilesList("xls,xlsx");
			su.setDeniedFilesList(DENIED);
			su.setMaxFileSize(SINGLEFILESIZE);
			su.setTotalMaxFileSize(TOTALMAXSIZE);
			
			//开始上传
			su.upload();
			
			//从smartupload请求中获取普通文本参数  text、number、password 即非  file
			if(su.getFiles()!=null && su.getFiles().getCount()>0){ //说明有文件要上传
				Files fs=su.getFiles();
				Collection<File> files=fs.getCollection();
				for(File fl:files){ //循环所有的文件
					if(!fl.isMissing()){
						filePath=PATH+"/"+new Date().getTime()+"_"+fl.getFileName();
						//上传文件
						fl.saveAs(pageContext.getServletContext().getRealPath("/")+filePath);
					}
				}
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	
	public int uploadStuInfoPhoto(PageContext pageContext){
		SmartUpload su=new SmartUpload();
		
		try {
			//初始化上传工具 
			su.initialize(pageContext);
			
			//设置参数
			su.setCharset("utf-8");
			su.setAllowedFilesList("jpg");
			su.setDeniedFilesList(DENIED);
			su.setMaxFileSize(SINGLEFILESIZE);
			su.setTotalMaxFileSize(TOTALMAXSIZE);
			
			//开始上传
			su.upload();
			
			//从smartupload请求中获取普通文本参数  text、number、password 即非  file
			if(su.getFiles()!=null && su.getFiles().getCount()>0){ //说明有文件要上传
				Files fs=su.getFiles();
				Collection<File> files=fs.getCollection();
				String filePath="";
				for(File fl:files){ //循环所有的文件
					if(!fl.isMissing()){
						filePath=PHOTOPATH+"/"+fl.getFileName();
						//上传文件
						fl.saveAs(pageContext.getServletContext().getRealPath("/")+filePath);
					}
				}
			}
			return 1;
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
