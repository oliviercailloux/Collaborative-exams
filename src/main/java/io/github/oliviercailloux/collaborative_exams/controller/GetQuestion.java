package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.mail.iap.Response;

import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@Path("Questions")
public class GetQuestion {
	
	@Path("all")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuestions() throws Exception {
		
		String result = "Les questions sont : ";
		List<Question> questions = data.getQuestions();
		
		for (Question q : questions)
		{
			result += " next : " + QuestionText.QuestionToJson(q);
		}
		return result;
	}

	@Path("Get/{id}")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getQuestion(@PathParam("id") int id) throws Exception {
		data.constructData();
		Question question = data.getQuestionByID(id);
		
		if (question == null || question.getId() == 0)
				return "Aucune Question n'existe sous cette identifiant";
		
		return QuestionText.QuestionToJson(question);
	}
}