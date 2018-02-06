package io.github.oliviercailloux.collaborative_exams.controller.ServletsModify;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

@Path("ChangeResponseTypeYN")
public class ChangeResponseTypeYN {



	@GET
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public int changeResponse()//@FormParam("id") int idQuestion, @FormParam("idAuthor") int idAuthor)
	throws Exception {
		//Question question = data.getQuestionByID(idQuestion);
		/*
		List<Answer> answers = question.getAnswers();
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		int i = 0;
		for (Answer a : answers) {
			if (a.isCorrect()) {
			Question newQuestion = new Question();
			newQuestion.setPhrasing(a.getText());
			newQuestion.setType(QuestionType.YN);
			newQuestion.setId(Question.questionCount++);
			
			newQuestion.setAuthor(data.getAuthorByID(idAuthor));
			ids.add(newQuestion.getId());
			}
		}
		
		question.setId(idQuestion * 100);

		data.addQuestion(question);
	*/
		return 1;
				//ids;
	}
		
		
}
	