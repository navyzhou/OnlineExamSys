package com.yc.onlineexamsys.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.onlineexamsys.biz.IMajorInfoBiz;
import com.yc.onlineexamsys.biz.impl.MajorInfoBizImpl;

@WebServlet("/majorInfo")
public class MajorInfoSerlvet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if ("findByPage".equals(op)){ //分页查询
			findByPage(request, response);
		} else if ("addMajorInfo".equals(op)){
			addMajorInfo(request, response);
		} else if ("updateMajorInfo".equals(op)){
			updateMajorInfo(request, response);
		} else if ("updateMajor".equals(op)){
			updateMajor(request,response);
		} else if ("findAll".equals(op)){
			findAll(request,response);
		}
	}

	/**
	 * 获取所有班级信息
	 * @param request
	 * @param response
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		IMajorInfoBiz majorInfoBiz = new MajorInfoBizImpl();
		this.send(response, majorInfoBiz.findAll());
	}

	private void updateMajor(HttpServletRequest request, HttpServletResponse response) {
		String mids = request.getParameter("mids");
		int status = Integer.parseInt(request.getParameter("status"));
		IMajorInfoBiz majorInfoBiz = new MajorInfoBizImpl();
		this.send(response, majorInfoBiz.updateMajorInfo(mids, status));
	}


	private void updateMajorInfo(HttpServletRequest request, HttpServletResponse response) {
		String mname = request.getParameter("mname");
		String mid = request.getParameter("mid");
		IMajorInfoBiz majorInfoBiz = new MajorInfoBizImpl();
		this.send(response, majorInfoBiz.updateMajorInfo(mid,mname));
	}

	private void addMajorInfo(HttpServletRequest request, HttpServletResponse response) {
		String mname = request.getParameter("mname");
		IMajorInfoBiz majorInfoBiz = new MajorInfoBizImpl();
		this.send(response, majorInfoBiz.addMajorInfo(mname));
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		//调用业务模型层进行处理
		IMajorInfoBiz majorInfoBiz = new MajorInfoBizImpl();
		this.send(response, majorInfoBiz.findByPage(pageNo, pageSize));
	}

}
