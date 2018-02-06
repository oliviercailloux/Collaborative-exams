package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
 
 


@Path("ChangeQuestionLanguage")
public class ChangeQuestionLanguage {
  
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getQuestion(@QueryParam("id") int idQuestion, @QueryParam("idAuthor") int idAuthor,@QueryParam("lang") String newLanguage) 
	throws Exception {
		data.constructData();
		
		Question question = data.getQuestions().get(0);
		/*
		Question question = new Question(data.getQuestionByID(idQuestion)); // A verifier
		
		question.setLanguage(newLanguage);
		question.setId(idQuestion * 100);

		data.addQuestion(question);
*/
		return QuestionText.QuestionToJson(question);
	}
}