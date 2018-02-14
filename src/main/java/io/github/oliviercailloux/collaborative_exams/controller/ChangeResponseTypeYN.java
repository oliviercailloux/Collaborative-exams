package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

@Path("ChangeResponseTypeYN")
public class ChangeResponseTypeYN {


    @Inject
    QuestionService questionService;

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestion(@PathParam("id") int id) throws Exception {
        data.constructData();
        Question question = data.getQuestionByID(id);
        Question myQuestion;

        myQuestion = new Question(question.getPhrasing(), question.getLanguage(), question.getAuthor(), QuestionType.YN, question.getCorrect());

        data.addQuestion(myQuestion);
        questionService.persist(myQuestion);
        return QuestionText.QuestionToJson(myQuestion);
    }


}
	