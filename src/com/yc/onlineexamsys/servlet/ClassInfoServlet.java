package com.yc.onlineexamsys.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.onlineexamsys.biz.IClassInfoBiz;
import com.yc.onlineexamsys.biz.impl.ClassInfoBizImpl;

@WebServlet("/classInfo")
public class ClassInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if ("findByPage".equals(op)){
			findByPage(request, response);
		} else if ("addClassesInfo".equals(op)){
			addClassInfo(request, response);
		} else if ("updateClassesInfo".equals(op)){
			updateClassesInfo(request, response);
		} else if ("findByCondition".equals(op)){
			findByCondition(request,response);
		}
	}

	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		String grade = request.getParameter("grade");
		String length = request.getParameter("length");
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		this.send(response, classInfoBiz.find(grade, mid, length, pageNo, pageSize));
	}

	private void updateClassesInfo(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		String mid = request.getParameter("mid");
		String grade = request.getParameter("grade");
		String length = request.getParameter("length");
		
		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		this.send(response, classInfoBiz.updateClassInfo(cid, cname, mid, grade, length));
	}

	private void addClassInfo(HttpServletRequest request, HttpServletResponse response) {
		String cname = request.getParameter("cname");
		String mid = request.getParameter("mid");
		String grade = request.getParameter("grade");
		String length = request.getParameter("length");
		
		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		this.send(response, classInfoBiz.addClassInfo(cname, mid, grade, length));
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		IClassInfoBiz classInfoBiz = new ClassInfoBizImpl();
		this.send(response, classInfoBiz.findByPage(pageNo, pageSize));
	}
}
