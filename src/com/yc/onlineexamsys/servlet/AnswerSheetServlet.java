package com.yc.onlineexamsys.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.onlineexamsys.bean.AnswerSheet;
import com.yc.onlineexamsys.bean.Questions;
import com.yc.onlineexamsys.bean.StuInfo;
import com.yc.onlineexamsys.biz.IAnswerSheetBiz;
import com.yc.onlineexamsys.biz.IQuestionBiz;
import com.yc.onlineexamsys.biz.impl.AnswerSheetBizImpl;
import com.yc.onlineexamsys.biz.impl.QuestionBizImpl;
import com.yc.onlineexamsys.util.SessionAttributeKey;

@WebServlet("/answerSheet")
public class AnswerSheetServlet extends BasicServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if ("findAnswerBySid".equals(op)) { // 查看分数
			findAnswerBySid(request, response);
		} else if ("findByPage".equals(op)) { // 分页查询
			findByPage(request, response);
		} else if ("findByCondition".equals(op)){ // 多条件组合查询
			findByCondition(request, response);
		} else if ("showAnswer".equals(op)) { // 查看答案
			showAnswer(request, response);
		}
	}

	private void showAnswer(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");

		IAnswerSheetBiz answerBiz = new AnswerSheetBizImpl();
		AnswerSheet sheet = answerBiz.findByAid(aid);

		if (sheet != null) {
			// 获取答案信息
			String ansStr = sheet.getAns();

			// 获取这套试题的试题编号
			String subjects = ansStr.replaceAll("-[A-Z0-9a-z\\u4e00-\\u9fa5]{1,}-[\\d]", "");

			// 得到这套试卷的每道题的编号  S_3,S_11,S_5,S_7,S_9

			// 添加单引号
			subjects = "'" + subjects.replace(",","','") + "'";


			// 每道题的答案，存到map中
			String[] ansArr = ansStr.split(",");
			Map<String, String> map = null; // 以题目编号为键， 以用户答案为值
			if (ansArr != null && ansArr.length > 0) {
				map = new HashMap<String, String>();
				String[] temp;
				for (String str : ansArr){
					temp = str.split("-");
					map.put(temp[0], temp[1]);
				}
			}

			// 获取这套试卷的试题信息
			IQuestionBiz questionBiz = new QuestionBizImpl();
			List<Questions> list = questionBiz.findByQids(subjects);

			// 将用户答案与试题绑定起来
			String[] types = {"错误","正确"};
			if (list != null && !list.isEmpty()) {
				for (Questions qs : list) {
					if (qs.getTid() == 3) {
						System.out.println();
						qs.setCname( types[Integer.parseInt(map.get(qs.getQid()))]);
						qs.setAns(types[Integer.parseInt(qs.getAns())]);
					} else {
						qs.setCname( map.get(qs.getQid())  );
					}
				}
			}

			HttpSession session = request.getSession();
			// 将答卷信息存到session
			session.setAttribute(SessionAttributeKey.ANSWERINfO, sheet);

			// 将试题信息存到session
			session.setAttribute(SessionAttributeKey.QUESTIONINFO, list);

			try {
				response.sendRedirect("front/page/show.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void findByCondition(HttpServletRequest request, HttpServletResponse response) {
		String mid = request.getParameter("mid");
		String grade = request.getParameter("grade");
		String cid = request.getParameter("cid");
		String semester = request.getParameter("semester");
		String courseId = request.getParameter("courseId");
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		int pageNo = Integer.parseInt( request.getParameter("page") );
		int pageSize = Integer.parseInt( request.getParameter("rows") );

		IAnswerSheetBiz answerBiz = new AnswerSheetBizImpl();
		this.send(response, answerBiz.findByCondition(mid, grade, semester, courseId, cid, sid, sname, pageNo, pageSize));
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) {
		int pageNo = Integer.parseInt( request.getParameter("page") );
		int pageSize = Integer.parseInt( request.getParameter("rows") );

		IAnswerSheetBiz answerBiz = new AnswerSheetBizImpl();
		this.send(response, answerBiz.findByPage(pageNo, pageSize));
	}

	private void findAnswerBySid(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		StuInfo stu = (StuInfo) session.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		String sid = stu.getSid();

		int pageNo = Integer.parseInt( request.getParameter("page") );
		int pageSize = Integer.parseInt( request.getParameter("rows") );

		IAnswerSheetBiz answerBiz = new AnswerSheetBizImpl();
		this.send(response, answerBiz.findPageBySid(sid, pageNo, pageSize));
	}
}
