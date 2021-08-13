package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Model.Model;

/**
 * Servlet implementation class Bankdeposit
 */
public class Bankdeposit extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get deposit amount from Deposit.jsp
		String depAmt = req.getParameter("depositAmt");
		
		String User = (String)req.getSession().getAttribute("name");
		String accno = (String)req.getSession().getAttribute("account_num");
		String pwd = (String)req.getSession().getAttribute("pw");
		
		//Test
		System.out.println(User+" "+accno);
		
		
		Model m = new Model();
		m.setDepAmt(depAmt);
		m.setName(User);
		m.setAcc_no(accno);
		m.setPwd(pwd); // for fetch()

		int D = m.BankDepos();
		
		System.out.println(D+" deposit made");
		
		if(D!=0) // Sucessfully deposited
		{
			m.fetch(); // retrieve username,bal,accno and email
			String newBal = m.getBal();
			System.out.println("New Bal: "+newBal);
			
			req.getSession().setAttribute("bankbal", newBal);
			req.getSession().setAttribute("update", "Deposit successful  SGD"+depAmt);
			
			// Redirect to homepage after successful login
			resp.sendRedirect("/BankWebMVC/yjbankhome.jsp");
		}
		else
		{
			req.getSession().setAttribute("update", "Deposit Unsuccessful.");
			resp.sendRedirect("/BankWebMVC/yjbankhome.jsp");
		}
		
		
	}
}
