package Controler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Entity.Answer;
import Entity.Author;
import Entity.Question;
import GsonHandler.AnswerSerialization;
import GsonHandler.AuthorSerialization;
import GsonHandler.QuestionSerialization;

@WebServlet("/Question")
public class GsonGetServlet extends HttpServlet {

	public String getGsonQuestion() {
		final GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeAdapter(Author.class, new AuthorSerialization());
		gsonBuilder.registerTypeAdapter(Answer.class, new AnswerSerialization());
		gsonBuilder.registerTypeAdapter(Question.class, new QuestionSerialization());

		gsonBuilder.setPrettyPrinting();

		final Gson gson = gsonBuilder.create();
		// Author:
		final Author myAuthor = new Author();
		myAuthor.setIdAuthor(1);
		myAuthor.setNameAuthor("Badga 3ebdelmajid lampoule");
		myAuthor.setEmail("Badga@gmail.com");

		final Answer myAnswer1 = new Answer();
		myAnswer1.setIdAnswer(1);
		myAnswer1.setText("Oui");
		myAnswer1.setCorrect(true);

		// Answer 2:
		final Answer myAnswer2 = new Answer();
		myAnswer2.setIdAnswer(2);
		myAnswer2.setText("Non");
		myAnswer2.setCorrect(false);

		// Question:
		final Question myQuestion = new Question();
		myQuestion.setIdQuestion(2);
		myQuestion.setAuthor(myAuthor);
		myQuestion.addAnswer(myAnswer1);
		myQuestion.addAnswer(myAnswer2);
		myQuestion.setLanguage("Français");
		myQuestion.setPhrasing("Est ce que .........?");

		return gson.toJson(myQuestion);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.print(getGsonQuestion());
		}
	}

}
