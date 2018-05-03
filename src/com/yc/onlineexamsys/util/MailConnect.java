package com.yc.onlineexamsys.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件发送的工具类
 * @author navy
 */
public class MailConnect{
	/**
	 * qq邮箱发送
	 * @param from_email：发送者邮箱
	 * @param pwd：发送者邮箱的授权码
	 * @param str：接受者邮箱，如果有多个，请有逗号隔开
	 * @param code：要发送的验证码
	 * @param name：用户姓名
	 * @return
	 */
	public static boolean sendQQmail(String from_email,String pwd,String str,String code,String name){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		try {
			// 获取系统当前属性
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp"); //设置发送邮件使用的协议 
			props.put("mail.smtp.host","smtp.qq.com");
			props.put("mail.smtp.auth","true");

			// 通过系统属性创建一个会话类
			Session session = Session.getInstance(props);

			//创建一个message对象，用来创建一封邮箱
			Message message=new MimeMessage(session);

			//设置邮件发送者邮箱地址
			message.setFrom(new InternetAddress(from_email));

			//设置邮件接收者的邮箱地址,假设只发送给一个人
			if(str.endsWith(",")){
				str=str.substring(0,str.lastIndexOf(",")-1);
			}
			String[]addr=str.split(",");
			InternetAddress[] to=new InternetAddress[addr.length];
			for(int i=0;i<addr.length;i++){
				to[i]=new InternetAddress(addr[i]);
			}


			//设置邮件发送的类型
			message.setRecipients(MimeMessage.RecipientType.TO,to);

			//邮件标题
			message.setSubject("源辰在线考试系统登录密码");
			message.setSentDate(new Date()); //设置时间

			String content="<span style=\"font-size:16px;font-weight:blod;font-family:'微软雅黑'\">尊敬的 "+name+" ，您好：</span><br /><br />" +
					"<div style='width:800px'>&nbsp;&nbsp;&nbsp;&nbsp;<span style=\"font-size:14px;font-family:'微软雅黑'\">" +
					"欢迎使用源辰在线考试系统密码重置功能，您的新密码为 "+code+"，请尽快使用该密码登陆系统后，重新修改密码！</span><br/><br /><span style=\"font-size:14px;font-family:'微软雅黑';float:right;\">"
							+ "源辰信息科技有限公司技术部</span><br/><span style=\"font-size:14px;font-family:'微软雅黑';float:right;padding-right:10px;\">"+sdf.format(new Date())+"</span></div>";

			Multipart mul=new MimeMultipart();  //新建一个MimeMultipart对象来存放多个BodyPart对象
			BodyPart mdp=new MimeBodyPart();  //新建一个存放信件内容的BodyPart对象
			mdp.setContent(content,"text/html;charset=utf-8");
			mul.addBodyPart(mdp);  //将含有信件内容的BodyPart加入到MimeMultipart对象中
			message.setContent(mul); //把mul作为消息内容
			message.saveChanges();

			//创建一个传输对象
			Transport transport=session.getTransport("smtp");

			//建立与服务器的链接
			transport.connect("smtp.qq.com",587,from_email,pwd);

			//发送邮件
			transport.sendMessage(message,message.getAllRecipients());

			//关闭邮件传输
			transport.close();
		} catch (Exception e) {
			LogUtil.log.error(e);
			return false;
		}
		return true;
	}
}
