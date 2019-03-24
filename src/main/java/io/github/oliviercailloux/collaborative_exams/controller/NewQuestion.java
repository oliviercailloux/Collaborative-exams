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

import io.github.oliviercailloux.collaborative_exams.Service.AnswerService;
import io.github.oliviercailloux.collaborative_exams.Service.IQuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Language.ConvertLanguage;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.FreeQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.IQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.MCQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.TrueFalseQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.YesNoQuestion;

@Path("NewQuestion")
public class NewQuestion {

	@Inject
	private IQuestionService questionService;

	@Inject
	private PersonService personService;
	
	@Inject
	private AnswerService answerService;
	

	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public void addQuestion(MultivaluedMap<String, String> form, @CookieParam("authorId") Cookie cookie)
			throws Exception {

		String type = form.getFirst("type");
		String phrasing = form.getFirst("phrasing");
		String language = form.getFirst("language");

		int idAuthor;
		int idAnswer1;
		int idAnswer2;
		
		
		/*
		 * Gestion des cookies
		 */
		if (cookie == null) {
			if (form.getFirst("idAuthor").isEmpty())
				throw new Exception(
						"Both Cookie and the input Author Id's field are null, please log-in or register again.");

			idAuthor = Integer.valueOf(form.getFirst("idAuthor"));
		} else {
			idAuthor = Integer.valueOf(cookie.getValue());
		}
		IQuestion question;
		question = null;
		//Boolean isCorrect;
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
			//isCorrect = Boolean.valueOf(form.getFirst("isCorrect"));
			
			 idAnswer1 = Integer.valueOf(form.getFirst("idAnswer1"));
			 idAnswer2 = Integer.valueOf(form.getFirst("idAnswer2"));
			 Answer answer1 = answerService.findAnswer(idAnswer1);
			 Answer answer2 = answerService.findAnswer(idAnswer2);




			question = new TrueFalseQuestion(phrasing, ConvertLanguage.convertLanguage(language), author, qType, answer1, answer2);
			break;

		case "YN":
			qType = QuestionType.YN;
			//isCorrect = Boolean.valueOf(form.getFirst("isCorrect"));
			
			 idAnswer1 = Integer.valueOf(form.getFirst("idAnswer1"));
			 idAnswer2 = Integer.valueOf(form.getFirst("idAnswer2"));
			 answer1 = answerService.findAnswer(idAnswer1);
			 answer2 = answerService.findAnswer(idAnswer2);
			
			 question = new YesNoQuestion(phrasing, ConvertLanguage.convertLanguage(language), author, qType, answer1, answer2);
			break;

		case "Free":
			qType = QuestionType.Free;
			int idAnswer = Integer.valueOf(form.getFirst("idAnswer"));
			Answer answer = answerService.findAnswer(idAnswer);
			question = new FreeQuestion(phrasing, ConvertLanguage.convertLanguage(language), author, qType, answer);
			break;

		case "QCM":
			qType = QuestionType.QCM;
			List<String> AnswersID = form.get("answersID");
			List<Answer> answers = new ArrayList<>();

			for (String s : AnswersID) {
				 answer = answerService.findAnswer(Integer.parseInt(s));
				 answers.add(answer);
			}
			question = new MCQuestion(phrasing, ConvertLanguage.convertLanguage(language), author, qType, answers);
			break;

		default:
			break;

		}

		questionService.persist(question);

		//return QuestionText.QuestionToJson(question);
	}

}
