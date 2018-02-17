package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

@Path("ChangeQuestionLanguage")
public class ChangeQuestionLanguage {

	@Inject
	QuestionService questionService;
	
	@Inject 
	PersonService personService; 

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String getQuestion(MultivaluedMap<String, String> form) throws Exception {

		int idQuestion = Integer.valueOf(form.getFirst("idQuestion"));
		int newAuthorId = Integer.valueOf(form.getFirst("newAuthorId"));
		String newLanguage = form.getFirst("newLanguage");

		Question question = questionService.findQuestion(idQuestion);
		Person newAuthor = personService.findPerson(newAuthorId);

		List<Answer> answers = new ArrayList<>();
        for (Answer a:question.getAnswers()
             ) {
            answers.add(new Answer(a.getText(),a.isCorrect()));
        }
	
		Question modifiedQuestion;

		QuestionType questionType = question.getType();

		if (questionType == QuestionType.TF || questionType == QuestionType.YN) {
			modifiedQuestion = new Question(question.getPhrasing(), newLanguage, newAuthor, question.getType(),
					question.getCorrect());
		} else if (questionType == QuestionType.Free) {
			modifiedQuestion = new Question(question.getPhrasing(), newLanguage, newAuthor, question.getType(),answers.get(0)
					);

		} else if (questionType == QuestionType.QCM) {
			modifiedQuestion = new Question(question.getPhrasing(), newLanguage, newAuthor, question.getType(), answers);
		} else {
			throw new Exception("Invalid Question Id ! ");
		}

		questionService.persist(modifiedQuestion);
		return String.valueOf(modifiedQuestion.getId());
	}
}