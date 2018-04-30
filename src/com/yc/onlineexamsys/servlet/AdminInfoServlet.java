package com.yc.onlineexamsys.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.onlineexamsys.bean.AdminInfos;
import com.yc.onlineexamsys.biz.IAdminInfoBiz;
import com.yc.onlineexamsys.biz.impl.AdminInfoBizImpl;
import com.yc.onlineexamsys.util.FileUploadUtil;
import com.yc.onlineexamsys.util.MD5Encryption;
import com.yc.onlineexamsys.util.MailConnect;
import com.yc.onlineexamsys.util.SessionAttributeKey;
import com.yc.onlineexamsys.util.StringUtil;

@WebServlet("/adminInfo")
public class AdminInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if ("adminReg".equals(op)) {  // 管理员注册
			adminReg(request, response);
		} else if ("findByPage".equals(op)) { // 分页查询
			findByPage(request, response);
		} else if ("findByAname".equals(op)) { // 根据名字模糊查询
			findByAname(request, response);
		} else if ("updatepwd".equals(op)) { // 修改密码
			updatepwd(request, response);
		} else if ("updateChangeStatus".equals(op)) { // 修改状态
			updateChangeStatus(request, response);
		} else if ("resetPwd".equals(op)) { // 发送验证码并重置密码
			resetPwd(request, response);
		} 
	}


	private void resetPwd(HttpServletRequest request, HttpServletResponse response) {
		String aid=request.getParameter("account");
		String  email=request.getParameter("email");
		
		IAdminInfoBiz adminInfoBiz = new  AdminInfoBizImpl();
		int result = -1;
		if (adminInfoBiz.getCountByEmail(aid, email) >0 ){ // 说明是注册用户，则将新密码发送到邮箱
			//生成验证码
			String code="";
			Random rd=new Random();
			while (code.length()<6) {
				code+=rd.nextInt(10);
			}
			//将验证码发到邮箱
			if (MailConnect.sendQQmail("1293580602@qq.com","dihpepdwtahlgefh",email, code, "")){
				// 如果发送成功，则修改数据库中的密码
				if ( adminInfoBiz.updatePwdByEmail(aid, code) > 0 ) { // 说明修改成功
					result = 3;
				} else { // 说明修改失败
					result = 2;
				}
			} else { // 说明邮件发送失败
				result = 1;
			}
		} else { // 说明账号不存在
			result = 0;
		}
		this.send(response, result);
	}

	private void updateChangeStatus(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");
		String status = request.getParameter("status");
		
		IAdminInfoBiz adminInfoBiz = new  AdminInfoBizImpl();
		this.send(response,adminInfoBiz.updateChangeStatus(aid, status));
	}

	private void updatepwd(HttpServletRequest request, HttpServletResponse response) {
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");

		HttpSession session = request.getSession();
		Object obj = session.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		int result = -1;
		if (obj == null) { // 说明没有登录
			result = 0;
		} else {
			if (StringUtil.isNull(oldpwd, newpwd )) { // 信息不完整
				result = 1;
			} else {
				AdminInfos adminInfo = (AdminInfos) obj;
				if ( !MD5Encryption.createPassword(oldpwd).equals(adminInfo.getPwd()) ) { // 原密码错误
					result = 2;
				} else {
					IAdminInfoBiz adminInfoBiz = new  AdminInfoBizImpl();
					if ( adminInfoBiz.updatePwd(oldpwd, newpwd, adminInfo.getAid()) > 0){
						adminInfo.setPwd(MD5Encryption.createPassword(newpwd));
						session.setAttribute(SessionAttributeKey.CURRENTLOGINUSER, adminInfo);
						result = 4; // 更新成功
					} else {
						result = 3; // 更新失败 
					}
				}
			}
		}
		this.send(response, result);
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
