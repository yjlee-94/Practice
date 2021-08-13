package com.controller;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// import javax.mail jar
/**
 * Servlet implementation class ForgotPwd
 */
public class ForgotPwd extends HttpServlet {
	Random rand = new Random();
	int otp= rand.nextInt(900000)+100000;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String toEmail = req.getParameter("email"); // from html
		
		String fromEmail= Credentials.email; //sender's mail id.
		String pwd = Credentials.pwd;		//sender's mail pwd.
		
		
		String subject="DO NOT REPLY: This is an reset password link"; // mail subject line
		String msg="Hi, user,\n here is the reset password link: http://localhost:8000/BankWebMVC/resetPwd.html"+"\n also enter this otp: "+otp; //mail body
		
		HttpSession sess = req.getSession(true);
		sess.setAttribute("email", toEmail);
		sess.setAttribute("otp", otp);
		
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
				return new PasswordAuthentication(fromEmail, pwd);
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
