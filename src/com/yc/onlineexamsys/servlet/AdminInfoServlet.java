package com.yc.onlineexamsys.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.onlineexamsys.biz.IAdminInfoBiz;
import com.yc.onlineexamsys.biz.impl.AdminInfoBizImpl;
import com.yc.onlineexamsys.util.FileUploadUtil;

@WebServlet("/adminInfo")
public class AdminInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if ("adminReg".equals(op)) {  // 管理员注册
			adminReg(request, response);
		} else if ("findByPage".equals(op)) { // 分页查询
			findByPage(request, response);
		} else if ("findByAname".equals(op)) {
			findByAname(request, response);
		}
	}

	private void findByAname(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		String aname = request.getParameter("aname");
		
		IAdminInfoBiz adminInfoBiz = new  AdminInfoBizImpl();
		this.send(response, adminInfoBiz.findByPage(aname, pageNo, pageSize));
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		
		IAdminInfoBiz adminInfoBiz = new  AdminInfoBizImpl();
		this.send(response, adminInfoBiz.findByPage(null, pageNo, pageSize));
	}

	private void adminReg(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil uploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this,request,response,null,true,1024,true);
		Map<String, String> map =  uploadUtil.upload(pageContext);
		
		IAdminInfoBiz adminInfoBiz = new  AdminInfoBizImpl();
		int result = adminInfoBiz.add(map.get("aname"), map.get("pwd"), map.get("email"), map.get("photo"));
		
		this.send(response, result);
	}

}
