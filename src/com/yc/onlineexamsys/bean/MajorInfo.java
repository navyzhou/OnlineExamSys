package com.yc.onlineexamsys.bean;

import java.io.Serializable;

/**
 * 专业信息
 * @author navy
 */
public class MajorInfo implements Serializable{
	private static final long serialVersionUID = 4745283560786859875L;
	private int mid;  //专业编号
	private String mname; //专业名称
	private int status;
	
	@Override
	public String toString() {
		return "MajorInfo [mid=" + mid + ", mname=" + mname + ", status=" + status + "]";
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public MajorInfo() {
		super();
	}

	public MajorInfo(int mid, String mname, int status) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mid;
		result = prime * result + ((mname == null) ? 0 : mname.hashCode());
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
		MajorInfo other = (MajorInfo) obj;
		if (mid != other.mid)
			return false;
		if (mname == null) {
			if (other.mname != null)
				return false;
		} else if (!mname.equals(other.mname))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
