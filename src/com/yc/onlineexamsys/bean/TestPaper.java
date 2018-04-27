package com.yc.onlineexamsys.bean;

import java.io.Serializable;

/**
 * 试卷信息
 * @author navy
 */
public class TestPaper implements Serializable{
	private static final long serialVersionUID = 8190062456648523229L;
	private int pid;
	private String pname;     //试卷名称
	private int cid; //课程编号
	private String testTime;   //开考试卷
	private int longExam;     //考试时长  分钟
	private String cids;      //考试班级编号  1,2,6,7
	private String subjects;    //题目信息 S_1-A-1,S_100-ABD-2,F_2-select-4,F_20-1-3 
	private String score;    //每种题型的分数 1-2;2-4;3-1;4-2  [0]=0 [1]=2  [2]=4 [3]=1  [4]=2
	private int status;     //未考、开考、考试中、已阅

	private String cname;

	@Override
	public String toString() {
		return "TestPaper [pid=" + pid + ", pname=" + pname + ", cid=" + cid + ", testTime=" + testTime + ", longExam="
				+ longExam + ", cids=" + cids + ", subjects=" + subjects + ", score=" + score + ", status=" + status
				+ ", cname=" + cname + "]";
	}

	/**
	 * 获取这套试卷的试卷编号
	 * @return
	 */
	public String getQuetionsIds(){
		if(subjects!=null && !"".equals(subjects)){
			String qids="";
			// 从 S_3-C-1,S_11-B-1,S_5-AD-2,S_7-0-3,S_9-多态-4  中获取所有题目的编号
			qids = subjects;
			qids = qids.replaceAll("-[A-Z0-9a-z\\u4e00-\\u9fa5]{1,}-[\\d]", "");

			// 得到这套试卷的每道题的编号  S_3,S_11,S_5,S_7,S_9
			// 添加单引号
			qids = "'" + qids.replace(",","','") + "'";

			// 得到 'S_3','S_11','S_5','S_7','S_9'
			return qids;
		}else{
			return "";
		}
	}

	/**
	 * 根据题型获取分数
	 * @return
	 */
	public int[] getSingleScore(){
		int[] scores=new int[5];
		if(score!=null && !"".equals(score)){
			String[] strs=score.split(";");
			String[] temp=null;
			for(String str:strs){
				temp=str.split("-");
				scores[ Integer.parseInt(temp[0]) ]=Integer.parseInt(temp[1]);
			}
		}
		return scores;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public int getLongExam() {
		return longExam;
	}

	public void setLongExam(int longExam) {
		this.longExam = longExam;
	}

	public String getCids() {
		return cids;
	}

	public void setCids(String cids) {
		this.cids = cids;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public TestPaper(int pid, String pname, int cid, String testTime, int longExam, String cids, String subjects,
			String score, int status) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.cid = cid;
		this.testTime = testTime;
		this.longExam = longExam;
		this.cids = cids;
		this.subjects = subjects;
		this.score = score;
		this.status = status;
	}

	public TestPaper() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cid;
		result = prime * result + ((cids == null) ? 0 : cids.hashCode());
		result = prime * result + longExam;
		result = prime * result + pid;
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + status;
		result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
		result = prime * result + ((testTime == null) ? 0 : testTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestPaper other = (TestPaper) obj;
		if (cid != other.cid)
			return false;
		if (cids == null) {
			if (other.cids != null)
				return false;
		} else if (!cids.equals(other.cids))
			return false;
		if (longExam != other.longExam)
			return false;
		if (pid != other.pid)
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (status != other.status)
			return false;
		if (subjects == null) {
			if (other.subjects != null)
				return false;
		} else if (!subjects.equals(other.subjects))
			return false;
		if (testTime == null) {
			if (other.testTime != null)
				return false;
		} else if (!testTime.equals(other.testTime))
			return false;
		return true;
	}
}
