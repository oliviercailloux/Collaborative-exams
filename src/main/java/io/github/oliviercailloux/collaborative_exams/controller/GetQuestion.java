package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;


@Path("Questions")
public class GetQuestion  {

    @Inject
    QuestionService questionService;

    @Context
    HttpServletRequest request;

    @Path("all")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getQuestions() throws Exception {
        List<Question> questions = questionService.getAll();
       return QuestionText.QuestionsToJson(questions);
    }

    @Path("/{id}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestion(@PathParam("id") int id) throws Exception {
        Question question = questionService.findQuestion(id);
        return QuestionText.QuestionToJson(question);
    }
}