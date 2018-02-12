package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.AnswerService;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@Path("hello")
public class HelloQuestion {

	@Inject
	private QuestionService questionService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() throws Exception {

		data.constructData();


		for (Question q:data.getQuestions()
			 ) {

			questionService.persist(q);
		}

		return ":D";
	}
	

}