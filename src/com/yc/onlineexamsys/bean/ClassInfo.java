package com.yc.onlineexamsys.bean;

import java.io.Serializable;

/**
 * 班级信息
 * @author navy
 */
public class ClassInfo implements Serializable{
	private static final long serialVersionUID = 1659882492218833815L;
	private int cid;
	private String cname;    //班级名称
	private int mid;   //所属专业
	private int grade ;   //年级
	private int length;   //学制
	private int status;
	
	private String mname; //专业名称

	@Override
	public String toString() {
		return "ClassInfo [cid=" + cid + ", cname=" + cname + ", mid=" + mid + ", grade=" + grade + ", length=" + length
				+ ", status=" + status + ", mname=" + mname + "]";
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public ClassInfo() {
		super();
	}

	public ClassInfo(int cid, String cname, int mid, int grade, int length, int status) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.mid = mid;
		this.grade = grade;
		this.length = length;
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cid;
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + grade;
		result = prime * result + length;
		result = prime * result + mid;
		result = prime * result + status;
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
		ClassInfo other = (ClassInfo) obj;
		if (cid != other.cid)
			return false;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (grade != other.grade)
			return false;
		if (length != other.length)
			return false;
		if (mid != other.mid)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
