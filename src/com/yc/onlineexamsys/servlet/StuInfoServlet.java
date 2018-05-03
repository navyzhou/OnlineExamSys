package com.yc.onlineexamsys.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.onlineexamsys.bean.StuInfo;
import com.yc.onlineexamsys.biz.IStuInfoBiz;
import com.yc.onlineexamsys.biz.impl.StuInfoBizImpl;
import com.yc.onlineexamsys.util.FileUploadUtil;
import com.yc.onlineexamsys.util.MD5Encryption;
import com.yc.onlineexamsys.util.ReadExcelToDB;
import com.yc.onlineexamsys.util.SessionAttributeKey;
import com.yc.onlineexamsys.util.StringUtil;

@WebServlet("/stuInfo")
public class StuInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if ("getInfos".equals(op)){ //获取专业、班级信息
			getInfos(request,response);
		} else if ("addStuInfos".equals(op)){ // 学生信息的批量上传
			addStuInfos(request,response);
		} else if ("addStuInfoPhotos".equals(op)){ // 学生图片的批量上传
			addStuInfoPhotos(request,response);
		} else if ("addStuInfo".equals(op)){ // 添加学生信息
			addStuInfo(request, response);
		} else if ("findByPage".equals(op)){ // 分页查询
			findByPage(request, response);
		} else if ("findByCondition".equals(op)){ // 条件查询
			findByCondition(request, response);
		} else if ("resetPwd".equals(op)){ // 重置密码
			resetPwd(request,response);
		} else if ("findInfoBySid".equals(op)) { // 查看个人信息
			findInfoBySid(request,response);
		} else if ("updatepwd".equals(op)) { // 修改密码
			updatepwd(request, response);
		} else if ("deleteStu".equals(op)) { // 删除学生信息
			deleteStu(request, response);
		}
	}
	
	private void deleteStu(HttpServletRequest request, HttpServletResponse response) {
		String sid = request.getParameter("sid");
		IStuInfoBiz stuInfoBiz = new  StuInfoBizImpl();
		this.send(response, stuInfoBiz.deleteStu(sid));
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
				StuInfo stuInfo = (StuInfo) obj;
				if ( !MD5Encryption.createPassword(oldpwd).equals(stuInfo.getPwd()) ) { // 原密码错误
					result = 2;
				} else {
					IStuInfoBiz stuInfoBiz = new  StuInfoBizImpl();
					if (stuInfoBiz.updatePwd(stuInfo.getSid(), oldpwd, newpwd) > 0){
						stuInfo.setPwd(MD5Encryption.createPassword(newpwd));
						session.setAttribute(SessionAttributeKey.CURRENTLOGINUSER, stuInfo);
						result = 4; // 更新成功
					} else {
						result = 3; // 更新失败 
					}
				}
			}
		}
		this.send(response, result);
	}

	private void findInfoBySid(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		if (obj == null) { // 说明没有登录
			this.send(response, "{\"err\":\"1\"}");
		} else {
			this.send(response, obj);
		}
	}

	private void resetPwd(HttpServletRequest request, HttpServletResponse response) {
		String sid = request.getParameter("sid");
		String pwd = request.getParameter("pwd");
		
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		this.send(response, stuInfoBiz.resetPwd(sid, pwd));
	}

	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String mid = request.getParameter("mid");
		String cid = request.getParameter("cid");
		String grade = request.getParameter("grade");
		
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		this.send(response, stuInfoBiz.findByCondition(sid, sname, mid, cid, grade, pageNo, pageSize));
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		this.send(response, stuInfoBiz.findByPage(pageNo, pageSize));
	}

	private void addStuInfo(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		String sname = request.getParameter("sname");
		String sex = request.getParameter("sex");
		String cardId = request.getParameter("cardId");
		String sid = request.getParameter("sid");
		String tel = request.getParameter("tel");
		
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		this.send(response, stuInfoBiz.addStuInfo(sid, sname, cid, sex, cardId, tel));
	}

	private void addStuInfoPhotos(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil uploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this,request,response,null,true,1024,true);
		int result =  uploadUtil.uploadStuInfoPhoto(pageContext);
		this.send(response,result);
	}

	private void addStuInfos(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil uploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this,request,response,null,true,1024,true);
		String filePath = uploadUtil.uploadExcelFile(pageContext);
		if (StringUtil.isNull(filePath)){
			this.send(response,-1);
		} else {
			File fl = new File(pageContext.getServletContext().getRealPath("/") + filePath);
			if (fl.exists()){
				ReadExcelToDB retdb = new ReadExcelToDB();
				IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
				List<List<String>> list = retdb.importExcel(fl);
				fl.deleteOnExit();
				this.send(response, stuInfoBiz.addStuInfos(list));
			}
		}
	}

	private void getInfos(HttpServletRequest request, HttpServletResponse response) {
		IStuInfoBiz stuInfoBiz = new StuInfoBizImpl();
		this.send(response, stuInfoBiz.getInfo());
	}

}
