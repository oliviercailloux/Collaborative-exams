package controller;

import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.TextQuestion;
import model.entity.question.Question;

/**
 * Servlet implementation class GetQuestion
 */
@WebServlet("/hello")
public class HelloQuestion extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
response.setContentType("text/html");
  PrintWriter out = response.getWriter();
  out.append("Your string goes here");
  out.close();
  
  
	}



}
