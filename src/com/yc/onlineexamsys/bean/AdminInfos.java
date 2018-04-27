package com.yc.onlineexamsys.bean;

import java.io.Serializable;

/**
 * 管理员信息
 * @author navy
 */
public class AdminInfos implements Serializable{
	private static final long serialVersionUID = -9000010417519424023L;
	private int aid;
	private int rid;    //所属角色
	private String aname;   //管理员姓名
	private String pwd;    //管理员密码
	private String email;   //管理员邮箱
	private String photo;    //管理员图像
	private int status;
	
	private String rname; //角色名称

	@Override
	public String toString() {
		return "AdminInfos [aid=" + aid + ", rid=" + rid + ", aname=" + aname + ", pwd=" + pwd + ", email=" + email
				+ ", photo=" + photo + ", status=" + status + ", rname=" + rname + "]";
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public AdminInfos(int aid, int rid, String aname, String pwd, String email, String photo, int status) {
		super();
		this.aid = aid;
		this.rid = rid;
		this.aname = aname;
		this.pwd = pwd;
		this.email = email;
		this.photo = photo;
		this.status = status;
	}

	public AdminInfos() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aid;
		result = prime * result + ((aname == null) ? 0 : aname.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + rid;
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
		AdminInfos other = (AdminInfos) obj;
		if (aid != other.aid)
			return false;
		if (aname == null) {
			if (other.aname != null)
				return false;
		} else if (!aname.equals(other.aname))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (rid != other.rid)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
