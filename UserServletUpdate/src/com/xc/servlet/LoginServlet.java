package com.xc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xc.domain.User;
import com.xc.service.UserService;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//调用service
		UserService us  = new UserService();
		User user = us.findUser(username,password);
		System.out.println(user);
		if(user!=null){
			request.setAttribute("user", user);
			request.getRequestDispatcher("/loginSuccess.jsp").forward(request, response);
		}else{
			//在页面友好提示
			response.getWriter().write("<font color='green' size='15'>用户名或密码错误，5s后跳回主页</font>");
			//5秒钟后自动返回登录页面
			response.setHeader("Refresh", "5;url="+request.getContextPath()+"/login.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);

	}

}
