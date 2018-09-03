package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

/**
 * Jax-RS Servlet that allows to change the language of a question
 */
@Path("ChangeQuestionLanguage")
public class ChangeQuestionLanguage {

	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;

	/**
	 *
	 * @param form               : contains a questionId, authorId that can be null
	 *                           if the cookie is set and the newLanguage
	 * @param authorIdFromCookie : contains the new authorId that can be null
	 * @return the Id of the new Question
	 * @throws Exception if the questionId is invalid
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String getQuestion(MultivaluedMap<String, String> form, @CookieParam("authorId") Cookie authorIdFromCookie)
			throws Exception {

		int newAuthorId;
		int idQuestion = Integer.valueOf(form.getFirst("idQuestion"));
		String newLanguage = form.getFirst("newLanguage");

		if (authorIdFromCookie == null) {
			if (form.getFirst("newAuthorId").isEmpty())
				throw new Exception(
						"Both Cookie and the input Author Id's field are null, please log-in or register again.");
			newAuthorId = Integer.valueOf(form.getFirst("newAuthorId"));
		} else {
			newAuthorId = Integer.valueOf(authorIdFromCookie.getValue());
		}

		Question question = questionService.findQuestion(idQuestion);
		Person newAuthor = personService.findPerson(newAuthorId);

		QuestionType questionType = question.getType();
		Question modifiedQuestion;
		List<Answer> answers = new ArrayList<>();
		if (questionType == QuestionType.TF || questionType == QuestionType.YN) {
			modifiedQuestion = new Question(question.getPhrasing(), newLanguage, newAuthor, question.getType(),
					question.getCorrect());
		} else if (questionType == QuestionType.Free) {

			for (Answer a : question.getAnswers()) {
				answers.add(new Answer(a.getText(), a.isCorrect()));
			}
			modifiedQuestion = new Question(question.getPhrasing(), newLanguage, newAuthor, question.getType(),
					answers.get(0));

		} else if (questionType == QuestionType.QCM) {
			for (Answer a : question.getAnswers()) {
				answers.add(new Answer(a.getText(), a.isCorrect()));
			}
			modifiedQuestion = new Question(question.getPhrasing(), newLanguage, newAuthor, question.getType(),
					answers);
		} else {
			throw new Exception("Invalid Question Id ! ");
		}
		questionService.persist(modifiedQuestion);
		return String.valueOf(modifiedQuestion.getId());
	}
}