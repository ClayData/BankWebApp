package com.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.CustRetriever;

public class LogInServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String custAddress = req.getParameter("email");
		String custPassword = req.getParameter("password");
		
		CustRetriever cr = new CustRetriever();
		int cust_id = cr.retrieveCust(custAddress, custPassword);
		
		if(cust_id > 0) {
			out.print("Valid User");
			HttpSession session = req.getSession();
			session.setAttribute("cust_id", cust_id);
			
			RequestDispatcher rd = req.getRequestDispatcher("transactions.html");
			rd.forward(req, res);
		} else {
			out.print("Invalid email/password");
			RequestDispatcher rd = req.getRequestDispatcher("login.html");
			rd.forward(req, res);
		}
	}
}
