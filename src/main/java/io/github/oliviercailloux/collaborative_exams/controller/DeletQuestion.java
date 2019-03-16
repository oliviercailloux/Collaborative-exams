package io.github.oliviercailloux.collaborative_exams.controller;



import javax.inject.Inject;
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
	 
	@Inject
	private QuestionService questionService;
	
	
	@Path("allQuestions")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response questDeleted() throws Exception {
		
	    questionService.deletAllQuestions();
		return Response.ok(Response.Status.OK).build();
		
	}
	
	@Path("/byId")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response  questionDeletedById(@QueryParam("id") int id) throws Exception {
		questionService.deletById(id);
		return Response.ok(Response.Status.OK).build();
	}
	
	
	@Path("/byAuthor")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response  questionDeletedByAuthor(@QueryParam("person") Person person) throws Exception {
    questionService.deletByAuthor(person);
    return Response.ok(Response.Status.OK).build();
	}
}