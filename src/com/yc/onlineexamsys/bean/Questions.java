package com.yc.onlineexamsys.bean;

import java.io.Serializable;

/**
 * 选择题对象
 * @author navy
 */
public class Questions implements Serializable{
	private static final long serialVersionUID = 8489782555837505702L;
	private String qid;
	private String qname;	//题目名称
	private int tid; 		//题目类型
	private int cid; 		//所属课程
	private String ans1;	//选项A
	private String ans2; 	//选项B
	private String ans3; 	//选项C
	private String ans4; 	//选项D
	private String ans; 	//正确答案
	private int status;
	
	private String tname; //题目类型名称
	private String cname; //所属课程名称
	
	@Override
	public String toString() {
		return "Questions [qid=" + qid + ", qname=" + qname + ", tid=" + tid + ", cid=" + cid + ", ans1=" + ans1
				+ ", ans2=" + ans2 + ", ans3=" + ans3 + ", ans4=" + ans4 + ", ans=" + ans + ", status=" + status
				+ ", tname=" + tname + ", cname=" + cname + "]";
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getAns1() {
		return ans1;
	}

	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	public String getAns2() {
		return ans2;
	}

	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	public String getAns3() {
		return ans3;
	}

	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}

	public String getAns4() {
		return ans4;
	}

	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Questions(String qid, String qname, int tid, int cid, String ans1, String ans2, String ans3, String ans4,
			String ans, int status) {
		super();
		this.qid = qid;
		this.qname = qname;
		this.tid = tid;
		this.cid = cid;
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.ans3 = ans3;
		this.ans4 = ans4;
		this.ans = ans;
		this.status = status;
	}

	public Questions() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ans == null) ? 0 : ans.hashCode());
		result = prime * result + ((ans1 == null) ? 0 : ans1.hashCode());
		result = prime * result + ((ans2 == null) ? 0 : ans2.hashCode());
		result = prime * result + ((ans3 == null) ? 0 : ans3.hashCode());
		result = prime * result + ((ans4 == null) ? 0 : ans4.hashCode());
		result = prime * result + cid;
		result = prime * result + ((qid == null) ? 0 : qid.hashCode());
		result = prime * result + ((qname == null) ? 0 : qname.hashCode());
		result = prime * result + status;
		result = prime * result + tid;
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
		Questions other = (Questions) obj;
		if (ans == null) {
			if (other.ans != null)
				return false;
		} else if (!ans.equals(other.ans))
			return false;
		if (ans1 == null) {
			if (other.ans1 != null)
				return false;
		} else if (!ans1.equals(other.ans1))
			return false;
		if (ans2 == null) {
			if (other.ans2 != null)
				return false;
		} else if (!ans2.equals(other.ans2))
			return false;
		if (ans3 == null) {
			if (other.ans3 != null)
				return false;
		} else if (!ans3.equals(other.ans3))
			return false;
		if (ans4 == null) {
			if (other.ans4 != null)
				return false;
		} else if (!ans4.equals(other.ans4))
			return false;
		if (cid != other.cid)
			return false;
		if (qid == null) {
			if (other.qid != null)
				return false;
		} else if (!qid.equals(other.qid))
			return false;
		if (qname == null) {
			if (other.qname != null)
				return false;
		} else if (!qname.equals(other.qname))
			return false;
		if (status != other.status)
			return false;
		if (tid != other.tid)
			return false;
		return true;
	}
}
