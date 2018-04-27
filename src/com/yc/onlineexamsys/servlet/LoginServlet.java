package com.yc.onlineexamsys.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.onlineexamsys.bean.AdminInfos;
import com.yc.onlineexamsys.bean.StuInfo;
import com.yc.onlineexamsys.biz.IAdminInfoBiz;
import com.yc.onlineexamsys.biz.IStuInfoBiz;
import com.yc.onlineexamsys.biz.impl.AdminInfoBizImpl;
import com.yc.onlineexamsys.biz.impl.StuInfoBizImpl;
import com.yc.onlineexamsys.util.FileUploadUtil;
import com.yc.onlineexamsys.util.SessionAttributeKey;

@WebServlet("/loginServlet")
public class LoginServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if ("userLogin".equals(op)){ // 用户登录
			userLogin(request, response);
		} else if ("loginOut".equals(op)){ // 安全退出
			loginOut(request, response);
		}
	}

	private void loginOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		session.invalidate();
		try {
			response.sendRedirect(request.getContextPath() + "/login.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void userLogin(HttpServletRequest request, HttpServletResponse response) {
		int role = Integer.parseInt(request.getParameter("role"));
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");

		int result = 0;
		if (role == 3){ // 说明是学生
			IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
			StuInfo stuInfo = stuInfoBiz.login(account, pwd);
			if (stuInfo != null){ // 说明登录成功
				result = 1; 
				HttpSession session = request.getSession();

				// 判断用户图片是否存在
				String photo = stuInfo.getPhoto();

				File fl = new File(request.getServletContext().getRealPath("/")+FileUploadUtil.PHOTOPATH,photo);
				if (!fl.exists()){
					stuInfo.setPhoto("");
				}
				session.setAttribute(SessionAttributeKey.CURRENTLOGINUSER, stuInfo);
			}

		} else {
			IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
			AdminInfos adminInfo = adminInfoBiz.login(account, pwd);

			if (adminInfo != null){
				result = 1;
				HttpSession session = request.getSession();
				session.setAttribute(SessionAttributeKey.CURRENTLOGINUSER, adminInfo);
			} 
		}

		// 否则说明账号或密码错误
		this.send(response, result);
	}
}
