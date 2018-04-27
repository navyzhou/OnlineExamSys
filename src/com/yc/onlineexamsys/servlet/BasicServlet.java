package com.yc.onlineexamsys.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void send(HttpServletResponse response,int result){
		PrintWriter out=null;
		
		try {
			out=response.getWriter();
			out.print(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	public void send(HttpServletResponse response,String str){
		PrintWriter out=null;
		
		try {
			out=response.getWriter();
			out.print(str);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	public void send(HttpServletResponse response,Object obj){
		PrintWriter out=null;
		
		try {
			Gson gson=new  Gson();
			out=response.getWriter();
			out.print(  gson.toJson(obj) );
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out!=null){
				out.close();
			}
		}
	}
}
