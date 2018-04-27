package com.yc.onlineexamsys.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.onlineexamsys.biz.IRoleInfoBiz;
import com.yc.onlineexamsys.biz.impl.RoleInfoBizImpl;

@WebServlet("/roleInfo")
public class RoleInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if ("findByPage".equals(op)){ // 分页查询
			findByPage(request, response);
		} else if ("findAll".equals(op)){ // 查询所有
			findAll(request, response);
		}
		
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		IRoleInfoBiz roleInfoBiz = new RoleInfoBizImpl();
		this.send(response, roleInfoBiz.findAll());
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		
		IRoleInfoBiz roleInfoBiz = new RoleInfoBizImpl();
		this.send(response, roleInfoBiz.findByPage(pageNo, pageSize));
	}
}
