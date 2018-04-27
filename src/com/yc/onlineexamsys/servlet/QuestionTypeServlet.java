package com.yc.onlineexamsys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.onlineexamsys.biz.IQuestionTypesBiz;
import com.yc.onlineexamsys.biz.impl.QuestionTypesBizImpl;

@WebServlet("/questionTypes")
public class QuestionTypeServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("findByPage".equals(op)){
			findByPage(request, response);
		}
	}

	private void findByPage(HttpServletRequest request,HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		IQuestionTypesBiz typesBiz = new QuestionTypesBizImpl();
		this.send(response, typesBiz.findByPage(pageNo, pageSize));
	}
}
