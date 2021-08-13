package com.controller;

import java.io.IOException;
import java.util.Properties;

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

/**
 * Servlet implementation class Banktransfer
 */
public class Banktransfer extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get values from Transfer.jsp for transfer()
		String transAmt = req.getParameter("TransferAmt"); // How much user is transferring
		String toAcc_no = req.getParameter("rpaccno");	// account number of the recepient 
		
		// Get values from the session() for fetch()
		String User = (String)req.getSession().getAttribute("name");
		String pwd = (String)req.getSession().getAttribute("pw");
		
		
		
		
		Model m = new Model();
		// set for fetch() to work
		m.setName(User);
		m.setPwd(pwd);
		
		m.fetch();
		
		// Set up mail's credentials 
		String toEmail = m.getEmail(); // from html
		String fromEmail= Credentials.email; //sender's mail id.
		String CrePwd = Credentials.pwd;	//sender's mail pwd.
		
		
		// retrieve the needed data.
		String userBal = m.getBal();
		String userAccno = m.getAcc_no();
		
		System.out.println(userBal);
		
		//Set the retrieve values
		m.setRep_accno(toAcc_no); // Set the recepient's acc number
		m.setTransAmt(transAmt); // set the amount to be transferred
		m.setAcc_no(userAccno); // set the user's acc number
		
		System.out.println(Integer.parseInt(transAmt));
		
		if(Integer.parseInt(transAmt) > Double.parseDouble(userBal)) // transfer amount exceeds balance
		{
			req.getSession().setAttribute("update", "Transfer unsuccessful insuffcient balance SGD"+userBal);
			
			// Redirect to homepage after unsuccessful transfer
			resp.sendRedirect("/BankWebMVC/yjbankhome.jsp");
		}
		else  // Balance is enough for the transfer proceed.
		{
			
			int t = m.BankTransfer();
			
			if(t==0 || t<0 ) // failed to transfer properly
			{
				System.out.println("Transfer execution failed");
				req.getSession().setAttribute("update", "Transfer execution failed");
				resp.sendRedirect("/BankWebMVC/yjbankhome.jsp");
			}
			else if (t==1)
			{
				System.out.println("Transfer execution failed to send");
				req.getSession().setAttribute("update", "Transfer execution failed to send");
				resp.sendRedirect("/BankWebMVC/yjbankhome.jsp");
			}
			else if (t==2)
			{
				m.fetch();
				String newBal = m.getBal();
				String tramt = m.getTransAmt();
				String accno = m.getAcc_no();
				String recepaccno = m.getRep_accno();
				String sender = m.getName();
				
				System.out.println("New Bal: "+newBal); // latest balance after transfer
				
				req.getSession().setAttribute("bankbal", newBal);
				System.out.println("Transfer execution succeeded");
				req.getSession().setAttribute("update", "Transfer execution succeeded");
				resp.sendRedirect("/BankWebMVC/yjbankhome.jsp");
				
				//Email construction subject and message
				String subject=" Alert from  yjbank(test) "+accno; 	// mail subject line";
				String msg = "Hi "+sender+" your transfer to accountno: "+recepaccno+" Amount of: $"+tramt+" was successful."+
						"\n Current balance: SGD"+newBal;
				
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
		
		
	}

}
