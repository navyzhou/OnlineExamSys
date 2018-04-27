package com.yc.onlineexamsys.bean;

import java.io.Serializable;

/**
 * 角色对象
 * @author navy
 */
public class RoleInfo implements Serializable{
	private static final long serialVersionUID = 9214809077579568156L;
	private int rid;
	private String rname;  //角色名称
	private int status;
	
	@Override
	public String toString() {
		return "RoleInfo [rid=" + rid + ", rname=" + rname + ", status=" + status + "]";
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public RoleInfo(int rid, String rname, int status) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.status = status;
	}

	public RoleInfo() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rid;
		result = prime * result + ((rname == null) ? 0 : rname.hashCode());
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
		RoleInfo other = (RoleInfo) obj;
		if (rid != other.rid)
			return false;
		if (rname == null) {
			if (other.rname != null)
				return false;
		} else if (!rname.equals(other.rname))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
