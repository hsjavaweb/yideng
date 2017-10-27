package com.xc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xc.service.UserService;
import com.xc.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//��ȡ������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//����service
		UserService us  = new UserServiceImpl();
		boolean user = us.findUser(username,password);
		System.out.println(user);
		if(user){
			request.getRequestDispatcher("/loginSuccess.jsp").forward(request, response);
		}else{
			//��ҳ���Ѻ���ʾ
			response.getWriter().write("<font color='green' size='15'>�û������������</font>");
			//5���Ӻ��Զ����ص�¼ҳ��
			response.setHeader("Refresh", "5;url="+request.getContextPath()+"/login.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);

	}

}
