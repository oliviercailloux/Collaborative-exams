package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.IQuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.IQuestionAdapter;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.IQuestion;

@Path("NewQuestionJson")
public class NewQuestionJson {

	@Inject
	IQuestionService questionService;

	@Inject
	IQuestionAdapter questionAdapter;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int newQuestionJson(JsonObject questionJson) throws Exception {

		IQuestion question = questionAdapter.adaptFromJson(questionJson);
		questionService.persist(question);
		//return IQuestionText.questionToJson(question);
		
		return question.getId();
	}

}
