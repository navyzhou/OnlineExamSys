package com.yc.onlineexamsys.bean;

import java.io.Serializable;

/**
 * 学生信息
 * @author navy
 */
public class StuInfo implements Serializable{
	private static final long serialVersionUID = 1994420438343076559L;
	private String sid;    //学号
	private String sname;  //姓名
	private String pwd;  //密码
	private int cid;  //所在班级
	private String sex;  //性别
	private String photo;  //图像
	private String cardID;  //身份证号码
	private String tel;  //联系方式
	private int status;

	private String cname; //班级名称
	private String mname; //专业名称
	private String grade; //年级
	private String mid;

	@Override
	public String toString() {
		return "StuInfo [sid=" + sid + ", sname=" + sname + ", pwd=" + pwd + ", cid=" + cid + ", sex=" + sex
				+ ", photo=" + photo + ", cardID=" + cardID + ", tel=" + tel + ", status=" + status + ", cname=" + cname
				+ ", mname=" + mname + ", grade=" + grade + "]";
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public StuInfo(String sid, String sname, String pwd, int cid, String sex, String photo, String cardID, String tel,
			int status) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.pwd = pwd;
		this.cid = cid;
		this.sex = sex;
		this.photo = photo;
		this.cardID = cardID;
		this.tel = tel;
		this.status = status;
	}

	public StuInfo() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardID == null) ? 0 : cardID.hashCode());
		result = prime * result + cid;
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		result = prime * result + status;
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
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
		StuInfo other = (StuInfo) obj;
		if (cardID == null) {
			if (other.cardID != null)
				return false;
		} else if (!cardID.equals(other.cardID))
			return false;
		if (cid != other.cid)
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
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		if (sname == null) {
			if (other.sname != null)
				return false;
		} else if (!sname.equals(other.sname))
			return false;
		if (status != other.status)
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}
}
