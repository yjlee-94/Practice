package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Model.Model;

/**
 * Servlet implementation class ResetPwd
 */
public class ResetPwd extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userOtp = Integer.parseInt(req.getParameter("otp"));
		String newpwd = req.getParameter("newpwd");
		String cnewpwd = req.getParameter("cnewpwd");
		
		HttpSession sess = req.getSession();
		String email = (String)sess.getAttribute("email");
		int otp = (int)sess.getAttribute("otp");
		System.out.println(otp+" "+userOtp);
		if(userOtp==otp)
		{
			if(newpwd.equals(cnewpwd))
			{	
				Model m = new Model();
			
				m.setEmail(email);
				m.setNewpwd(newpwd);
				
				int x = m.resetPwd();
				if(x==1) // Your password has been ressetted
				{
					sess.setAttribute("msg", "Your password has been resetted");
					System.out.println("Password resetted");
					resp.sendRedirect("/BankWebMVC/Pwreset.jsp");
				}
				else
				{ // Password reset failed internal error contact administrator
					sess.setAttribute("msg", "Password reset failed internal error contact administrator");
					System.out.println("Password not resetted");
					resp.sendRedirect("/BankWebMVC/Pwreset.jsp");
				}
			} // Your passwords do not match
			else
			{
				sess.setAttribute("msg", "Your passwords do not match");
				System.out.println("Password does not match");
				resp.sendRedirect("/BankWebMVC/Pwreset.jsp");
			}
		}
		else // Please try again OTP incorrect
		{
			sess.setAttribute("msg", "Please try again OTP incorrect");
			System.out.println("Wrong otp");
			resp.sendRedirect("/BankWebMVC/Pwreset.jsp");
		}
	}
}