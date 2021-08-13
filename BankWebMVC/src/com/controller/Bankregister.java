package com.controller;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Model.Model;
import java.util.Properties;
import java.util.Random;
import java.util.*;
/**
 * Servlet implementation class Bankregister
 */
public class Bankregister extends HttpServlet {
	
	Random rand = new Random();
	int accno = rand.nextInt(999999998)+1;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String toEmail = req.getParameter("email"); // from html
		String fromEmail= Credentials.email; //sender's mail id.
		String CrePwd = Credentials.pwd;	//sender's mail pwd.
		
		
		//Retrieve these values from Bankreg.html
		String name = req.getParameter("name"); 
		String email = req.getParameter("email");
		String un = req.getParameter("un"); 
		String pwd = req.getParameter("pwd");
		String cpwd = req.getParameter("cpwd");
		
		if(pwd.equals(cpwd)) // new account pw and confirm pw matches
		{
			Model m = new Model();
			// Set these values to Model.java private variables
			String accountno = Integer.toString(accno);
			m.setName(name);
			m.setEmail(email);
			m.setUn(un);
			m.setPwd(pwd);
			m.setCpwd(cpwd);
			m.setAcc_no(accountno);
			
			int x = m.Bankregister(); // run the method and get the return in x
			if(x==0) 
			{
				resp.sendRedirect("BankWebMVC/dnreg.html");
			}
			else // User successfully registered
			{
				
				m.fetch(); // Fetch the registered user's data for the email
				String accNo = m.getAcc_no();
				String bal = m.getBal();
				String usernm = m.getUn();
				String cus = m.getName();
				
				//Email construction subject and message
				String subject=" Welcome to yjbank(test) "+cus; // mail subject line";
				String msg = "Your login details are "+usernm+" account no: "+accNo+
						"current balance: "+bal;
				
				resp.sendRedirect("/BankWebMVC/successReg.html");
				
				//Creating Session Object
				Properties prop = new Properties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
				prop.put("mail.smtp.port", 587);
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true");

				Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
				{
					protected PasswordAuthentication getPasswordAuthentication()
					{
						//sender's mail id and pwd is encapsulated inside "SendersCredentials.java"
						return new PasswordAuthentication(fromEmail, CrePwd);
					}
				});
				
				try {
					//Composing the Mail
					MimeMessage mesg = new MimeMessage(session);
					mesg.setFrom(new InternetAddress(fromEmail));
					mesg.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
					mesg.setSubject(subject);  
					mesg.setText(msg);  
					
					//Sending the Mail
					Transport.send(mesg);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				
			}
		}
		else
		{
			
			resp.sendRedirect("/BankWebMVC/dnreg.html");
		}
	}

}
