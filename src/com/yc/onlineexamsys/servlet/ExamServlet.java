package com.yc.onlineexamsys.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.onlineexamsys.bean.Questions;
import com.yc.onlineexamsys.bean.StuInfo;
import com.yc.onlineexamsys.bean.TestPaper;
import com.yc.onlineexamsys.biz.IAnswerSheetBiz;
import com.yc.onlineexamsys.biz.IQuestionBiz;
import com.yc.onlineexamsys.biz.ITestPaperBiz;
import com.yc.onlineexamsys.biz.impl.AnswerSheetBizImpl;
import com.yc.onlineexamsys.biz.impl.QuestionBizImpl;
import com.yc.onlineexamsys.biz.impl.TestPaperBizImpl;
import com.yc.onlineexamsys.util.SessionAttributeKey;
import com.yc.onlineexamsys.util.StringUtil;

@WebServlet("/examServlet")
public class ExamServlet extends BasicServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if ("joinExam".equals(op)){ // 参加考试，准备考试试卷信息
			joinExam(request, response);
		} else if("assignment".equals(op)){ //交卷
			assignment(request, response);
		}
	}

	private void assignment(HttpServletRequest request, HttpServletResponse response) {
		String ans = request.getParameter("ans"); // 答案
		int longTime = Integer.parseInt( request.getParameter("longTime") ); // 剩余时长

		// 从session中获取当前考试试卷编号
		HttpSession session = request.getSession();
		TestPaper testPaper = (TestPaper) session.getAttribute(SessionAttributeKey.TESTPAPERINFO);
		String pid = String.valueOf(testPaper.getPid());

		// 从session中获取当前考试的学生学号
		StuInfo stuInfo = (StuInfo) session.getAttribute(SessionAttributeKey.CURRENTLOGINUSER);
		String sid = stuInfo.getSid();

		// 统计分数
		// 获取这套试卷每道题型的分数信息
		String[] scoreStr = testPaper.getScore().split(";"); // 1-10  2-15  3-5  4-5

		int[] scores = new int[scoreStr.length+1];
		String[] temp;
		for(String str : scoreStr){
			temp = str.split("-");
			if (!StringUtil.isNull(temp[1])) {
				scores[Integer.parseInt(temp[0])] = Integer.parseInt(temp[1]);
			}
		}

		// scores  => scores[0]=0  scores[1] = 10  scores[2] = 15 scores[3] = 5  scores[4] = 5

		// 所得分数
		int score = 0;

		// 获取这套试卷的答案信息
		String correctAns = ","+testPaper.getSubjects()+","; 

		// 获取学生提交的每道题的答案
		String[] stuAns = ans.split(","); 
		String singleAns;
		
		for(String str : stuAns){ // 判断每道题的答案是否正确   Q_1-B-1
			temp = str.split("-"); // temp[0]="Q_1" 题目编号  temp[1]="B" 用户答案   temp[2]="1" 题型

			if(StringUtil.isNull( temp[1].trim() )){ // 说明未作答
				continue;
			} else {
				if ("2".equals(temp[2])){ // 说明是多选题
					if (correctAns.contains(","+str+",")){ // 说明是全对
						score += scores[Integer.parseInt(temp[2])]; // ,Q_20-CD-2,
					} else { // 可能是全错或对一半
						// 获取这一题在正确答案信息
						
						singleAns = correctAns.substring(correctAns.indexOf(temp[0]+"-")); //  Q_20-  Q_2-  Q_20-CD-2,Q_4-AD-2,Q_6-0-3,Q_8-多态-4,
						singleAns = singleAns.substring(0, singleAns.indexOf(",")); // Q_20-CD-2

						// 获取正确答案
						singleAns = singleAns.split("-")[1]; // CD

						if (singleAns.length() > temp[1].length() && singleAns.contains(temp[1])){ // 如果长度没有正确答案长，且包含在正确答案里面，说明此题对一半
							score += scores[Integer.parseInt(temp[2])]/2;

						} // 否则，如果长度有这么长，而不能完全匹配说明里面有错误选项。如果长度比正确答案长，也说明有错误选项。如果长度比正确答案小，且不包含在正确答案里面，说明选错了
					}

				} else { // 说明是其他题型
					if (correctAns.contains(","+str+",")){ // 说明是正确答案
						score += scores[Integer.parseInt(temp[2])];
					}
				}
			}
		}
		
		session.removeAttribute(SessionAttributeKey.TESTPAPERINFO);
		session.removeAttribute(SessionAttributeKey.QUESTIONINFO);

		// 将答卷信息添加到数据库
		IAnswerSheetBiz answerSheetBiz = new AnswerSheetBizImpl();
		this.send(response, answerSheetBiz.add(pid, sid, ans, score, longTime));
	}

	private void joinExam(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid"); // 获取要参加的考试试卷编号

		// 根据试卷编号，获取试卷信息
		ITestPaperBiz testPaperBiz = new TestPaperBizImpl();
		TestPaper testPaper = testPaperBiz.findTestPaperByPid(pid);

		if (testPaper == null){
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println("<script>alert('暂无考试信息...');window.close();</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null){
					out.close();
				}
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute(SessionAttributeKey.TESTPAPERINFO, testPaper); // 将这套试卷信息存到session中

			String subjects = testPaper.getQuetionsIds();

			// 从数据库中查询出这些题目
			IQuestionBiz questionBiz = new QuestionBizImpl();
			List<Questions> list = questionBiz.findByQids(subjects);
			session.setAttribute(SessionAttributeKey.QUESTIONINFO, list);
			try {
				response.sendRedirect("front/page/exam.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
