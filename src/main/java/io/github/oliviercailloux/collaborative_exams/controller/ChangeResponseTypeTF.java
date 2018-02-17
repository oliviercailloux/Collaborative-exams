package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
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
	public String getQuestion(MultivaluedMap<String, String> form,@CookieParam("authorId") String cookie) throws Exception {
		int idQuestion = Integer.valueOf(form.getFirst("idQuestion"));
		int newAuthorId ;

		if(cookie==null)
			newAuthorId = Integer.valueOf(form.getFirst("newAuthorId"));
		else
			newAuthorId = Integer.valueOf(cookie);

		Question question = questionService.findQuestion(idQuestion);
		Person newAuthor = personService.findPerson(newAuthorId);
        //Boolean correct =  ;
		Question modifiedQuestion;
		QuestionType questionType = question.getType();

        	if(questionType == QuestionType.YN){
			modifiedQuestion = new Question(question.getPhrasing(), question.getLanguage(), newAuthor, QuestionType.TF,
					question.getCorrect());
		}else if (questionType == QuestionType.TF) {
			throw new Exception("Question type is already TF ! ");
		} else throw new Exception("This type of question can't change");

		questionService.persist(modifiedQuestion);
		return String.valueOf(modifiedQuestion.getId());

	}
}
	