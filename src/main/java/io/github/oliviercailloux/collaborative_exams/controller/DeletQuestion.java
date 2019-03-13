package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;



/**
 * @author Mohamed
 * Servlet that allows to delete questions from DB 
 */
@Path("DeleteQuestion")
public class DeletQuestion {
	private boolean isdelet ; 
	@Inject
	private QuestionService questionService;
	
	
	@Path("allQuestions")
	@DELETE
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response questDeleted() throws Exception {
		
		this.isdelet = questionService.deletAllQuestion();
		if(isdelet==false)
			throw new Exception("Questions is not deleted from DB");
		return Response.ok(Response.Status.OK).build();
		
	}
	
	@Path("/byId")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response  questionDeletedById(@QueryParam("id") int id) throws Exception {
		this.isdelet=questionService.deletById(id);
		if(isdelet==false)
			throw new Exception("Questions is not deleted from DB");
		return Response.ok(Response.Status.OK).build();
	}
	
	
	@Path("/byAuthor")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response  questionDeletedByAuthor(@QueryParam("person") Person person) throws Exception {
	this.isdelet=	questionService.deletByAuthor(person);
		if(isdelet==false)
			throw new Exception("Questions is not deleted from DB");
		return Response.ok(Response.Status.OK).build();
	}
}