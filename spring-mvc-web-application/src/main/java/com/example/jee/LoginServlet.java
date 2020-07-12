package com.example.jee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.UserValidationService;

@WebServlet(urlPatterns="/login.do")
public class LoginServlet extends HttpServlet {
	
	UserValidationService service = new UserValidationService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		
		if(service.isUserValid(userName, password)) {
			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
		}else {
			request.setAttribute("errorMessage", "Invalid Credentials");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			
		}
		request.setAttribute("name", request.getParameter("name"));
			
		}
}
