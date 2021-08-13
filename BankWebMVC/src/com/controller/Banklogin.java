package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Model.Model;

/**
 * Servlet implementation class Banklogin
 */
public class Banklogin extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String username = req.getParameter("un"); // Retrieve username entered from Banklogin.html
		String pwd = req.getParameter("pwd");	// Retrieve password entered from Banklogin.html
		
		Model m = new Model();
		m.setUn(username); // pass the values to Model()
		m.setPwd(pwd);    // pass the value to Model()
		
		int x = m.Banklogin();
		
		if(x==0)
		{
			resp.sendRedirect("/BankWebMVC/Banklogin.html");
		}
		else // Login successful 
		{
			// Retrieve values from model()
			String name = m.getName();
			String accno = m.getAcc_no();
			String blc = m.getBal();
			
			// Create a new session
			HttpSession session = req.getSession(true);
			
			// Pass the values to the session
			session.setAttribute("name", name);
			session.setAttribute("account_num",accno);
			session.setAttribute("bankbal", blc);
			session.setAttribute("pw", pwd);
			
			
			// Redirect to homepage after successful login
			resp.sendRedirect("/BankWebMVC/yjbankhome.jsp");
		}
	}

}
