package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;


/**
 * @author Mohamed
 * Servlet that allows to refresh DB 
 */
@Path("DeletQuestionPerson")
public class DeletAllQuestionPerson {
	private boolean isdelet ; 

	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;
	
	
//	
//	@Path("allQuestPerson")
//	@GET
//	@Consumes(MediaType.TEXT_PLAIN)
//	@Produces(MediaType.TEXT_PLAIN)
//	public boolean questDeleted() throws Exception {
//		this.isdelet= questionService.deletAllQuestion();
//		 personService.deletAllPerson();
//		if(!isdelet)
//			throw new Exception("Questions is not deleted from DB");
//		return isdelet;
//		
//		
//	}
	
}
