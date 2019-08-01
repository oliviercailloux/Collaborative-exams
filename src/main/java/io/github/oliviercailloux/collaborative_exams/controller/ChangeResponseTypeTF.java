package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

/**
 * Jax-RS Servlet that allows to change a Yes/no question to a True/false question
 */
@Path("ChangeResponseTypeTF")
public class ChangeResponseTypeTF {

	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;

	/**
	 * Using @FormParam inject form data in method arguments
	 * 
	 * @param idQuestion
	 * @param cookieIdAuthor
	 * @param authorIdFromCookie : contains the new authorId that can be null
	 * @return id of modified question
	 * @throws Exception if the type of the question is already TF or the type
	 *                   cannot be changed to TF or IdQuestion is invalid
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String getTFquestionID(@QueryParam("idQuestion") Integer idQuestion, @QueryParam("idAuthor") Integer newIdAuthor, @CookieParam("authorId") Cookie cookieIdAuthor)
			throws Exception {
		int QuestionId = Integer.valueOf(idQuestion);
		int newAuthorId;

		if (cookieIdAuthor == null) {
			if (newIdAuthor == null)
				throw new Exception("Both Cookie and the input Author Id's field are null, please log-in or register again.");

			newAuthorId = Integer.valueOf(newIdAuthor);
		} else {
			newAuthorId = Integer.valueOf(cookieIdAuthor.getValue());
		}

		Question question = questionService.findQuestion(QuestionId);
		Person newAuthor = personService.findPerson(newAuthorId);
		Question modifiedQuestion;
		QuestionType questionType = question.getType();

		if (questionType == QuestionType.YN) {
			modifiedQuestion = new Question(question.getPhrasing(), question.getLanguage(), newAuthor, QuestionType.TF,
					question.getCorrect());
		} else if (questionType == QuestionType.TF) {
			throw new Exception("Question type is already TF ! ");
		} else
			throw new Exception("This type of question can't change from" + question.getType() + " to TF");

		questionService.persist(modifiedQuestion);
		return String.valueOf(modifiedQuestion.getId());

	}
}
