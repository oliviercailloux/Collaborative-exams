package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.QuestionText;
import model.entity.question.Question;

/**
 * Servlet implementation class NewQuestion
 */
@WebServlet("/NewQuestion")
public class NewQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewQuestion() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int questionCount = Question.questionCount++;
		Question question;
		try {
			question = QuestionText.JsonToQuestion(request.getParameter("question"));
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

		question.setId(questionCount);
		// TextQuestion.writeQuestion(question);
		request.setAttribute("id", questionCount);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
