package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@Path("Multi")
public class QuestionMultiFormat {
	
	@Inject
	QuestionService questionService;
	
	@Path("QuestionMultiFormat")
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Question getQuestion(@QueryParam("idQuestion") Integer idQuestion, @QueryParam("idAuthor") Integer authorId, 
			@CookieParam("authorId") Cookie cookieIdAuthor)throws Exception {
			
		int author;	
		if (cookieIdAuthor == null) {
			if (authorId == null) {
				throw new WebApplicationException("Both Cookie and the input Author Id's field are null, please log-in or register again.");
			} else {
				author = authorId;
			}
		} else {
			author = Integer.valueOf(cookieIdAuthor.getValue());
		}
		
		Question question = questionService.findQuestion(idQuestion);
		
		return question;
	}	
}
