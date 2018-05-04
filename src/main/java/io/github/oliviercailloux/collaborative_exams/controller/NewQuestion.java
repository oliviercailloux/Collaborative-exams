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
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

@Path("NewQuestion")
public class NewQuestion {

	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addQuestion(MultivaluedMap<String, String> form, @CookieParam("authorId") Cookie cookie)
			throws Exception {

		String type = form.getFirst("type");
		String phrasing = form.getFirst("phrasing");
		String language = form.getFirst("language");

		int idAuthor;

		if (cookie == null) {
			if (form.getFirst("idAuthor").isEmpty())
				throw new Exception(
						"Both Cookie and the input Author Id's field are null, please log-in or register again.");

			idAuthor = Integer.valueOf(form.getFirst("idAuthor"));
		} else {
			idAuthor = Integer.valueOf(cookie.getValue());
		}
		Question question = new Question();
		Boolean isCorrect;
		QuestionType qType;

		// Author get
		Person author = personService.findPerson(idAuthor);
		/*
		 * if (author == null) throw new Exception("L'auteur saisi n'existe pas");
		 */
		if (author == null)
			author = new Person("UnknownAuthor");

		switch (type) {
		case "TF":
			qType = QuestionType.TF;
			isCorrect = Boolean.valueOf(form.getFirst("isCorrect"));
			question = new Question(phrasing, language, author, qType, isCorrect);
			break;

		case "YN":
			qType = QuestionType.YN;
			isCorrect = Boolean.valueOf(form.getFirst("isCorrect"));
			question = new Question(phrasing, language, author, qType, isCorrect);
			break;

		case "Free":
			qType = QuestionType.Free;
			String freeAnswer = form.getFirst("freeAnswer");
			question = new Question(phrasing, language, author, qType, (new Answer(freeAnswer, true)));
			break;

		case "QCM":
			qType = QuestionType.QCM;
			List<String> AnswersText = form.get("answersText");
			List<String> answersCorrect = form.get("answersCorrect");
			List<Answer> answers = new ArrayList<>();

			int i = 0;
			for (String s : AnswersText) {
				answers.add(new Answer(s, Boolean.valueOf(answersCorrect.get(i))));
				i++;
			}
			question = new Question(phrasing, language, author, qType, answers);
			break;

		default:
			break;

		}

		questionService.persist(question);

		return QuestionText.QuestionToJson(question);
	}

}
