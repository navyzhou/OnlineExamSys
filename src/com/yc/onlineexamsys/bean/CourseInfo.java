package com.yc.onlineexamsys.bean;

import java.io.Serializable;

/**
 * 课程信息
 * @author navy
 */
public class CourseInfo implements Serializable{
	private static final long serialVersionUID = 7098639434095692934L;
	private int cid;    //课程编号
	private String cname;    //课程名称
	private int semester;   //开设的学期 1,2,3,4,5,6,7,8
	private int status;
	
	@Override
	public String toString() {
		return "CourseInfo [cid=" + cid + ", cname=" + cname + ", semester=" + semester + ", status=" + status + "]";
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

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public CourseInfo(int cid, String cname, int semester, int status) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.semester = semester;
		this.status = status;
	}

	public CourseInfo() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cid;
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + semester;
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
		CourseInfo other = (CourseInfo) obj;
		if (cid != other.cid)
			return false;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (semester != other.semester)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
