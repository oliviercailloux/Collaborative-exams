package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import helper.QuestionText;
import model.entity.data;
import model.entity.question.Question;

@Path("GetQuestion")
public class GetQuestion {

	@Path("Get")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getQuestion(@QueryParam("id") int id) throws Exception {
		Question question = data.getQuestionByID(id);
		return QuestionText.QuestionToJson(question);
	}
}