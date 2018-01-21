package io.github.oliviercailloux.collaborative_exams.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;



@Path("NewQuestion")
public class NewQuestion {

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public int newQuestion(String question) throws Exception {
		Question questionFormatted = QuestionText.JsonToQuestion(question);
		int newID = questionFormatted.getId() * 100;
		questionFormatted.setId(newID);
		data.addQuestion(questionFormatted);
		int i = data.getQuestionCount();
		
		return newID;
	}

}
