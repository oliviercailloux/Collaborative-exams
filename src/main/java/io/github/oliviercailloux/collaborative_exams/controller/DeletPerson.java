package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;

@Path("DeleteQuestion")
public class DeletPerson {
	
	
	private boolean isdelet ; 
	@Inject
	private QuestionService questionService;
	
	
	@Path("allQuestions")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response questDeleted() throws Exception {
		
		this.isdelet = questionService.deletAllQuestion();
		if(isdelet==false)
			throw new Exception("Questions is not deleted from DB");
		return Response.ok(Response.Status.OK).build();
		
	}
	
	

}
