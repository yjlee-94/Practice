package com.Model;

import java.sql.*;

public class Model {
	
	Connection con; 
	PreparedStatement pstmt;
	ResultSet res;
	
	public Model() // Model.java constructor Connects to DB
	{
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yjbank","root","howdoYouturnthison@9"); // use database yjbank
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private String name; // User's name
	private String email; // User's email address
	private String un;	// User's username
	private String newpwd; // User's new password for resetting
	private String pwd; // User's entered password
	private String cpwd; // User's entered confirm password
	private String bal; // User's current account balance
	private String acc_no; // User's account number
	private String depAmt; // Deposit amount
	private String transAmt; // Transfer amount
	private String rep_accno; // Recepient account number
	
	public String getDepAmt() {
		return depAmt;
	}
	public void setDepAmt(String depAmt) {
		this.depAmt = depAmt;
	}
	public String getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}
	public String getRep_accno() {
		return rep_accno;
	}
	public void setRep_accno(String rep_accno) {
		this.rep_accno = rep_accno;
	}
	public String getBal() {
		return bal;
	}
	public void setBal(String bal) {
		this.bal = bal;
	}
	public String getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUn() {
		return un;
	}
	public void setUn(String un) {
		this.un = un;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCpwd() {
		return cpwd;
	}
	public void setCpwd(String cpwd) {
		this.cpwd = cpwd;
	}
	
	public void fetch() // name has to be set first.
	{	
		try {
		String sql = "SELECT * FROM accounts WHERE name=? AND password=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,name);
		pstmt.setString(2,pwd);
		
		res = pstmt.executeQuery();
		if(res.next()==true)
		{
			email = res.getString("email");
			un = res.getString("username");
			bal = res.getString("balance");
			acc_no = res.getString("acc_num");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	
	public int Bankregister() // Bank register module
	{
		try {
			String sql="Insert into accounts(name,email,username,password,acc_num) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, un);
			pstmt.setString(4, pwd);
			pstmt.setString(5, acc_no);
			
			int x = pstmt.executeUpdate();
			return x;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public int Banklogin() { // Bank login module
		try {
			
			String sql= "SELECT * FROM accounts WHERE username=? and password=?";
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1,un);
			pstmt.setString(2, pwd);
		
			res = pstmt.executeQuery();
			if(res.next()==true)
			{
				name = res.getString("name"); // if found get the name of the user who logged in and set it in model's variable
				acc_no = res.getString("acc_num");
				bal = res.getString("balance");
				
				return 1;
			}
			else
			{
				return 0;
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public int BankDepos() {
			
		try {
				String sql ="UPDATE accounts set balance=balance+? WHERE name=? AND acc_num=?";
				pstmt = con.prepareStatement(sql); 
				pstmt.setString(1,depAmt);
				pstmt.setString(2, name);
				pstmt.setString(3, acc_no);
				
				int x = pstmt.executeUpdate();
			
				return x;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return 0;
	}
	
	public int BankTransfer() {
		
		try {
				String sql = "UPDATE accounts set balance=balance-? WHERE name=? AND acc_num=?";
				pstmt = con.prepareStatement(sql); 
				pstmt.setString(1,transAmt);
				pstmt.setString(2,name);
				pstmt.setString(3,acc_no);
				
				int i = pstmt.executeUpdate(); // ideally i = 1
				System.out.println(i+" rows affected subtracting"); // bug handling
				
				if(i>0) // first execution works
				{
					try 
					{
						String sql2 = "UPDATE accounts set balance=balance+? WHERE acc_num=?";
						pstmt = con.prepareStatement(sql2); 
						pstmt.setString(1,transAmt);
						pstmt.setString(2,rep_accno);
						
						int j = pstmt.executeUpdate(); // ideally j = 1
						
						System.out.println(j+" rows affected Adding");
						
						return j+i; // 2
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.out.print("First sql did not work");
				}
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		return 0;
	}
	
	public int resetPwd()
	{
		try {
			String sql = "Update accounts set password=? where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newpwd);
			pstmt.setString(2, email);
		
			int x = pstmt.executeUpdate();
			return x;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		return 0;
	}
	
	
	
}
