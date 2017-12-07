package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.TextQuestion;
import model.entity.Person;
import model.entity.question.Question;

/**
 * Servlet implementation class ChangeQuestionLanguage
 */
@WebServlet("/ChangeQuestionLanguage")
public class ChangeQuestionLanguage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeQuestionLanguage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		Locale lang = Locale.forLanguageTag(request.getParameter("language")); // a verifier
		Question question = TextQuestion.getQuestion(id);
		Person author = new Person();
		author.setId(authorId);
		question.setAuthor(author);
		question.setId(Question.questionCount++);
		question.setLanguage(lang);
		TextQuestion.writeQuestion(question);	
		request.setAttribute("id", question.getId());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
