package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.IQuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.IQuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.IQuestion;

/*
 * 
 * @author Amine BOUKALA
 */

@Path("IQuestions")
public class GetQuestion {

	@Inject
	IQuestionService questionService;

	@Context
	HttpServletRequest request;

	@Path("all")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getQuestions() throws Exception {
		List<IQuestion> questions = questionService.getAll();
		return IQuestionText.questionsToJson(questions);
	}

	@Path("/get")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getQuestion(@QueryParam("id") int id) throws Exception {
		IQuestion question = questionService.findQuestion(id);
		return IQuestionText.questionToJson(question);
	}
}