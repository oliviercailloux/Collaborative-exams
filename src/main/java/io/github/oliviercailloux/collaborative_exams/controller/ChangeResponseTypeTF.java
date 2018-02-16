package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.ArrayList;
import java.util.List;

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

@Path("ChangeResponseTypeTF")
public class ChangeResponseTypeTF {
	
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
		

		Question question = questionService.findQuestion(idQuestion);
		Person newAuthor = personService.findPerson(newAuthorId);
		
		QuestionType tf = QuestionType.TF;
	
		Question modifiedQuestion; 

		QuestionType questionType = question.getType();

		if (questionType == QuestionType.YN) {
			modifiedQuestion = new Question(question.getPhrasing(), question.getLanguage(), newAuthor, tf,
					question.getCorrect());
		} else if (questionType == QuestionType.Free) {
			//Comment gerer les reponses ?
			modifiedQuestion = new Question(question.getPhrasing(), question.getLanguage(), newAuthor, tf,
					question.getAnswers().get(0));

		} else if (questionType == QuestionType.QCM) {
			//Comment gerer les reponses ?
			modifiedQuestion = new Question(question.getPhrasing(), question.getLanguage(), newAuthor, tf,
					question.getAnswers());
		} else if (questionType == QuestionType.TF) {
			throw new Exception("Question type is already TF ! ");
		}else {
			throw new Exception("Invalid Question Id ! ");
		}
		
		questionService.persist(modifiedQuestion);
		return String.valueOf(modifiedQuestion.getId());
			
	}
		
		
}
	