package com.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AddAccount;
import database.AddUser;

public class SignUpServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String custName = req.getParameter("cust_name");
		String custGender = req.getParameter("cust_gender");
		int salary = Integer.parseInt(req.getParameter("salary"));
		int custAge =  Integer.parseInt(req.getParameter("cust_age"));
		String custPassword = req.getParameter("cust_password");
		String custEmail = req.getParameter("cust_email");
		String acct = req.getParameter("acct");
		
		AddUser au = new AddUser();
		au.addCustomer(custName, custGender, salary, custPassword, custEmail, custAge);
		
	}
}
