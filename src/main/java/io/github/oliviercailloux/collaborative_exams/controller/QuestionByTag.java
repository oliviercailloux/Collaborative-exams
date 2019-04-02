package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@Path("QuestionByTag")
public class QuestionByTag {
	
	@Inject
	private QuestionService questionService; 
	
	@Inject
	private PersonService personService;
	
	
	@Path("ByTag")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Question> getQuestions(@QueryParam("idAuthor") int idAuthor, @QueryParam("tag") String tag)throws Exception {
		
		Person person = personService.findPerson(idAuthor);
		
		List<Question> questionsByTag = questionService.findQuestionTag(person, tag);
		
		return questionsByTag;
	}
}
