package com.yc.onlineexamsys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.onlineexamsys.bean.Questions;
import com.yc.onlineexamsys.bean.StuInfo;
import com.yc.onlineexamsys.bean.TestPaper;
import com.yc.onlineexamsys.biz.IQuestionBiz;
import com.yc.onlineexamsys.biz.ITestPaperBiz;
import com.yc.onlineexamsys.biz.impl.QuestionBizImpl;
import com.yc.onlineexamsys.biz.impl.TestPaperBizImpl;
import com.yc.onlineexamsys.util.SessionAttributeKey;
import com.yc.onlineexamsys.util.StringUtil;

@WebServlet("/testPaper")
public class TestPaperServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if ("getInfos".equals(op)){ // 获取专业、班级、课程信息
			getInfos(request,response);
		} else if ("addTestPaper".equals(op)) {  // 自动组卷
			addTestPaper(request, response);
		} else if ("findByPage".equals(op)) { // 查询试卷信息
			findByPage(request, response);
		} else if ("changeTestPaperStatus".equals(op)) { // 修改试卷状态
			changeTestPaperStatus(request, response);
		} else if ("deleteTestPaper".equals(op)) {// 删除试卷
			deleteTestPaper(request,response);
		} else if ("findTestPaperBySidAndStatus".equals(op)) { // 根据学号查询未考的试卷信息
			findTestPaperBySidAndStatus(request,response);
		} else if ("findByCondition".equals(op)) { // 多条件组合查询
			findByCondition(request,response);
		} else if ("showTestPaper".equals(op)) { // 查看试卷信息
			showTestPaper(request,response);
		}
	}

	private void showTestPaper(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");

		ITestPaperBiz testPaperBiz = new TestPaperBizImpl();
		TestPaper testPaper =  testPaperBiz.findTestPaperByPid(pid);

		if (testPaper != null){

			IQuestionBiz questionBiz = new QuestionBizImpl();
			List<Questions>  questions = questionBiz.findByQids( testPaper.getQuetionsIds() );

			HttpSession session = request.getSession();
			session.setAttribute(SessionAttributeKey.TESTPAPERINFO, testPaper);
			session.setAttribute(SessionAttributeKey.QUESTIONINFO, questions);
		}
		
		try {
			response.sendRedirect("back/manager/page/show.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		String cid = request.getParameter("cid");
		String semester = request.getParameter("semester");
		String courseId = request.getParameter("courseId");
		String status = request.getParameter("status");
		int pageNo = Integer.parseInt( request.getParameter("page") );
		int pageSize = Integer.parseInt( request.getParameter("rows") );

		ITestPaperBiz testPaperBiz = new TestPaperBizImpl();
		this.send(response, testPaperBiz.findByCondition(semester, courseId, cid, status, pageNo, pageSize));
	}

	private void findTestPaperBySidAndStatus(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		if (obj != null){
			StuInfo stuInfo = (StuInfo) obj;
			int cid = stuInfo.getCid();

			String sid = stuInfo.getSid();

			ITestPaperBiz testPaperBiz = new TestPaperBizImpl();
			this.send(response, testPaperBiz.findTestPaperBySidAndStatus(String.valueOf(cid),sid));
		}
	}

	private void deleteTestPaper(HttpServletRequest request, HttpServletResponse response) {
		String pid =request.getParameter("pid");

		ITestPaperBiz testPaperBiz = new TestPaperBizImpl();
		this.send(response, testPaperBiz.deleteTestPaper(pid));
	}

	private void changeTestPaperStatus(HttpServletRequest request, HttpServletResponse response) {
		String pid =request.getParameter("pid");
		String status =request.getParameter("status");

		ITestPaperBiz testPaperBiz = new TestPaperBizImpl();
		this.send(response, testPaperBiz.updateTestPaperStatus(pid, status));
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		ITestPaperBiz testPaperBiz = new TestPaperBizImpl();
		this.send(response, testPaperBiz.findByPage(pageNo, pageSize));
	}

	private void addTestPaper(HttpServletRequest request, HttpServletResponse response) {
		String pname = request.getParameter("qname");
		String cid = request.getParameter("cid");
		String testTime = request.getParameter("testTime");
		String longExam = request.getParameter("longExam");
		String cids = request.getParameter("cids");
		String score = request.getParameter("score");
		String rnum = request.getParameter("rnum");
		String cnum = request.getParameter("cnum");
		String jnum = request.getParameter("jnum");
		String fnum = request.getParameter("fnum");

		ITestPaperBiz testPaperBiz = new TestPaperBizImpl();
		// 获取该课程对应类型的试题的题号
		List<Map<String, String>> list = testPaperBiz.findQuestionByCid(cid);

		List<String> radioList = new ArrayList<String>();
		List<String> checkBoxList = new ArrayList<String>();
		List<String> judgeList = new ArrayList<String>();
		List<String> fillList = new ArrayList<String>();

		if (list != null && !list.isEmpty()){
			for (Map<String,String> map : list) {
				if ("1".equals(map.get("tid"))){
					radioList.add(map.get("qid"));
				} else if ("2".equals(map.get("tid"))) {
					checkBoxList.add(map.get("qid"));
				}else if ("3".equals(map.get("tid"))) {
					judgeList.add(map.get("qid"));
				}else if ("4".equals(map.get("tid"))) {
					fillList.add(map.get("qid"));
				}
			}

			String subjects = "";
			if ( !StringUtil.isNull(rnum) && Integer.parseInt(rnum) <= radioList.size()) {
				Collections.shuffle(radioList);
				for (int i = 0, len = Integer.parseInt(rnum); i < len; i++){
					subjects += radioList.get(i) + ",";
				}
			}

			if ( !StringUtil.isNull(cnum) && Integer.parseInt(cnum) <= checkBoxList.size()) {
				Collections.shuffle(checkBoxList);
				for (int i = 0, len = Integer.parseInt(cnum); i < len; i++){
					subjects += checkBoxList.get(i) + ",";
				}
			}

			if ( !StringUtil.isNull(jnum) && Integer.parseInt(jnum) <= judgeList.size()) {
				Collections.shuffle(judgeList);
				for (int i = 0, len = Integer.parseInt(jnum); i < len; i++){
					subjects += judgeList.get(i) + ",";
				}
			}

			if ( !StringUtil.isNull(fnum) && Integer.parseInt(fnum) <= fillList.size()) {
				Collections.shuffle(fillList);
				for (int i = 0, len = Integer.parseInt(fnum); i < len; i++){
					subjects += fillList.get(i) + ",";
				}
			}

			if ("".equals(subjects)){
				this.send(response, -1);
			} else {
				subjects = subjects.substring(0, subjects.length() - 1);
				this.send(response, testPaperBiz.addTestPaper(pname, cid, testTime, longExam, cids, subjects, score));
			}
		} else {
			this.send(response, -1); // 题量不足
		}
	}


	private void getInfos(HttpServletRequest request,HttpServletResponse response) {
		ITestPaperBiz testPaperBiz = new TestPaperBizImpl();
		this.send(response, testPaperBiz.getInfos());
	}
}
