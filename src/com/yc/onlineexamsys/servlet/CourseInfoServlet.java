package com.yc.onlineexamsys.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.onlineexamsys.biz.ICourseInfoBiz;
import com.yc.onlineexamsys.biz.impl.CourseInfoBizImpl;

@WebServlet("/courseInfo")
public class CourseInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		
		if ("findByPage".equals(op)){ //分页查询
			findByPage(request,response);
		} else if ("addCourseInfo".equals(op)){ //添加课程
			addCourseInfo(request,response);
		} else if ("updateCourseInfo".equals(op)){ //修改课程
			updateCourseInfo(request,response);
		} else if ("updateCourse".equals(op)){ //启停课程
			updateCourse(request,response);
		} else if ("findByCondition".equals(op)){ //条件查询
			findByCondition(request,response);
		} else if ("findAll".equals(op)) {  // 查询所有课程
			findAll(request, response); 
		}
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		ICourseInfoBiz courseInfoBiz=new CourseInfoBizImpl();
		this.send(response,courseInfoBiz.finds());
	}

	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		String cname=request.getParameter("cname");
		String semester=request.getParameter("semester");
		int status=Integer.parseInt(request.getParameter("status"));
		
		ICourseInfoBiz courseInfoBiz=new CourseInfoBizImpl();
		this.send(response, courseInfoBiz.findByPage(pageNo, pageSize, cname, semester, status));
	}

	private void updateCourse(HttpServletRequest request, HttpServletResponse response) {
		String cids=request.getParameter("cids");
		int status=Integer.parseInt(request.getParameter("status"));
		ICourseInfoBiz courseInfoBiz=new CourseInfoBizImpl();
		this.send(response,courseInfoBiz.updateCourseInfo(cids, status));
	}

	private void updateCourseInfo(HttpServletRequest request, HttpServletResponse response) {
		String cid=request.getParameter("cid");
		String cname=request.getParameter("cname");
		String semester=request.getParameter("semester");
		ICourseInfoBiz courseInfoBiz=new CourseInfoBizImpl();
		this.send(response,courseInfoBiz.updateCourseInfo(cid, cname, semester));
	}

	/**
	 * 添加课程
	 * @param request
	 * @param response
	 */
	private void addCourseInfo(HttpServletRequest request, HttpServletResponse response) {
		String cname=request.getParameter("cname");
		String semester=request.getParameter("semester");
		ICourseInfoBiz courseInfoBiz=new CourseInfoBizImpl();
		this.send(response,courseInfoBiz.addCourseInfo(cname, semester));
	}

	/**
	 * 返回所有课程信息
	 * @param request
	 * @param response
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		int pageNo=Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		ICourseInfoBiz courseInfoBiz=new CourseInfoBizImpl();
		this.send(response,courseInfoBiz.findByPage(pageNo, pageSize));
	}
}
