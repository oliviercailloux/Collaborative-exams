package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import helper.QuestionText;
import model.entity.data;
import model.entity.question.Question;

@Path("NewQuestion")
public class NewQquestion {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String newQuestion(Question question) throws Exception {
		//Question question = data.getQuestion(1);

		return QuestionText.QuestionToJson(question);
	}
}
