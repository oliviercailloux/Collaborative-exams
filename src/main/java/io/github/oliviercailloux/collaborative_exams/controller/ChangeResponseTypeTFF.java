package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
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

@Path("ChangeResponseTypeTF")
public class ChangeResponseTypeTFF {



	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public List<Integer> changeResponse(@QueryParam("id") int idQuestion, @QueryParam("idAuthor") int idAuthor)
	throws Exception {
		Question question = data.getQuestionByID(idQuestion);
		
		List<Answer> answers = question.getAnswers();
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		int i = 0;
		for (Answer a : answers) {
			if (a.isCorrect()) {
			Question newQuestion = new Question();
			newQuestion.setPhrasing(a.getText());
			newQuestion.setType(QuestionType.TF);
			newQuestion.setId(Question.questionCount++);
			
			newQuestion.setAuthor(data.getAuthorByID(idAuthor));
			ids.add(newQuestion.getId());
			}
		}
		
		question.setId(idQuestion * 100);

		data.addQuestion(question);

		return ids;
	}
		
		
}
	