package com.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BankTransactionsServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String choice = req.getParameter("choice");
		
		if(choice.toLowerCase() == "deposit") {
			RequestDispatcher rd = req.getRequestDispatcher("send");
			rd.forward(req, res);
		} else if (choice.toLowerCase() == "withdraw") {
			RequestDispatcher rd = req.getRequestDispatcher("send");
			rd.forward(req, res);
		} else if (choice.toLowerCase() == "transfer") {
			RequestDispatcher rd = req.getRequestDispatcher("send");
			rd.forward(req, res);
		} else if (choice.toLowerCase() == "statement") {
			RequestDispatcher rd = req.getRequestDispatcher("send");
			rd.forward(req, res);
		}
	}
}
