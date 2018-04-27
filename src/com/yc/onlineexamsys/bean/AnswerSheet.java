package com.yc.onlineexamsys.bean;

import java.io.Serializable;

/**
 * 答卷信息
 * @author navy
 */
public class AnswerSheet implements Serializable{
	private static final long serialVersionUID = -5537795761452730858L;
	private int aid; //答卷编号
	private int pid;     //试卷编号
	private String sid;    //学生学号
	private String ans;   //答案  S_1-B-1,S_100-ABD-2,F_2-select-4,F_20-0-3
	private double score; //得分
	private int surplus;   //剩余时长
	private int status;
	
	private String sname; //学生姓名
	private String pname; //试卷名称
	
	@Override
	public String toString() {
		return "AnswerSheet [aid=" + aid + ", pid=" + pid + ", sid=" + sid + ", ans=" + ans + ", score=" + score
				+ ", surplus=" + surplus + ", status=" + status + ", sname=" + sname + ", pname=" + pname + "]";
	}
	
	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getSurplus() {
		return surplus;
	}

	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public AnswerSheet(int aid, int pid, String sid, String ans, double score, int surplus, int status) {
		super();
		this.aid = aid;
		this.pid = pid;
		this.sid = sid;
		this.ans = ans;
		this.score = score;
		this.surplus = surplus;
		this.status = status;
	}

	public AnswerSheet() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aid;
		result = prime * result + ((ans == null) ? 0 : ans.hashCode());
		result = prime * result + pid;
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result + status;
		result = prime * result + surplus;
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
		AnswerSheet other = (AnswerSheet) obj;
		if (aid != other.aid)
			return false;
		if (ans == null) {
			if (other.ans != null)
				return false;
		} else if (!ans.equals(other.ans))
			return false;
		if (pid != other.pid)
			return false;
		if (Double.doubleToLongBits(score) != Double.doubleToLongBits(other.score))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		if (status != other.status)
			return false;
		if (surplus != other.surplus)
			return false;
		return true;
	}
}
