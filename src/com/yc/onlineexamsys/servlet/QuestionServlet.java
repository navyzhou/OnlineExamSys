package com.yc.onlineexamsys.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.onlineexamsys.biz.IQuestionBiz;
import com.yc.onlineexamsys.biz.impl.QuestionBizImpl;
import com.yc.onlineexamsys.util.FileUploadUtil;
import com.yc.onlineexamsys.util.ReadExcelToDB;
import com.yc.onlineexamsys.util.StringUtil;

@WebServlet("/question")
public class QuestionServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if ("getInfos".equals(op)) { // 获取课程和题目类型信息
			getInfos(request, response);
		} else if ("addQuestions".equals(op)) { // 批量导入试题信息
			addQuestions(request, response);
		} else if ("addQuestionfo".equals(op)) { // 单个添加试题信息
			addQuestionfo(request, response);
		} else if ("findQuestionNumByCid".equals(op)) { // 根据课程编号获取该课程每种题型的题量
			findQuestionNumByCid(request, response);
		} else if ("findByPage".equals(op)) { // 分页查询
			findByPage(request, response);
		} else if ("findByCondition".equals(op)) { // 多条件组合查询
			findByCondition(request, response);
		} else if ("updateQuestion".equals(op)) { // 修改
			updateQuestion(request, response);
		} else if ("deleteQuestion".equals(op)) { // 删除
			deleteQuestion(request, response);
		}
	}
	
	private void deleteQuestion(HttpServletRequest request, HttpServletResponse response) {
		String qid = request.getParameter("qid");
		
		IQuestionBiz questionBiz = new QuestionBizImpl();
		this.send(response, questionBiz.deleteQuestion(qid));
	}

	private void updateQuestion(HttpServletRequest request, HttpServletResponse response) {
		String qid = request.getParameter("qid");
		String qname = request.getParameter("qname");
		String tid = request.getParameter("tid");
		String cid = request.getParameter("cid");
		String ans1 = request.getParameter("ans1");
		String ans2 = request.getParameter("ans2");
		String ans3 = request.getParameter("ans3");
		String ans4 = request.getParameter("ans4");
		String ans = request.getParameter("ans");
		
		IQuestionBiz questionBiz = new QuestionBizImpl();
		this.send(response, questionBiz.updateQuestion(qid, qname, tid, cid, ans1, ans2, ans3, ans4, ans));
	}

	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		
		String cid = request.getParameter("cid"); // 课程编号
		String tid = request.getParameter("tid"); //题型编号
		String qname = request.getParameter("qname"); //题型编号
		
		IQuestionBiz questionBiz = new QuestionBizImpl();
		this.send(response, questionBiz.findByCondition(tid, cid, qname, pageNo, pageSize));
		
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		
		IQuestionBiz questionBiz = new QuestionBizImpl();
		this.send(response, questionBiz.findByPage(pageNo, pageSize));
	}

	private void findQuestionNumByCid(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		IQuestionBiz questionBiz = new QuestionBizImpl();
		List<Map<String, String>> list = questionBiz.getTotals(cid);
		this.send(response, list);
	}

	private void addQuestionfo(HttpServletRequest request, HttpServletResponse response) {
		String tid = request.getParameter("tid");
		String cid = request.getParameter("cid");
		String qname = request.getParameter("qname");
		String ans1 = request.getParameter("ans1");
		String ans2 = request.getParameter("ans2");
		String ans3 = request.getParameter("ans3");
		String ans4 = request.getParameter("ans4");
		String ans = request.getParameter("ans");
		IQuestionBiz questionBiz = new QuestionBizImpl();
		this.send(response, questionBiz.addQuestion(qname, tid, cid, ans1, ans2, ans3, ans4, ans));
	}

	private void addQuestions(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil uploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this,request,response,null,true,1024,true);
		String filePath = uploadUtil.uploadExcelFile(pageContext);
		if (StringUtil.isNull(filePath)){
			this.send(response,-1);
		} else {
			File fl = new File(pageContext.getServletContext().getRealPath("/") + filePath);
			if (fl.exists()){
				ReadExcelToDB retdb = new ReadExcelToDB();
				IQuestionBiz questionBiz = new QuestionBizImpl();
				List<List<String>> list = retdb.importExcel(fl);
				fl.deleteOnExit();
				this.send(response,questionBiz.addQuestions(list));
			}
		}
	}

	private void getInfos(HttpServletRequest request, HttpServletResponse response) {
		IQuestionBiz questionBiz = new QuestionBizImpl();
		this.send(response, questionBiz.getInfos());
	}

}
