package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;


@Path("ChangeQuestionLanguage")
public class ChangeQuestionLanguage {

    @Inject
    QuestionService questionService;

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestion(@PathParam("id") int id) throws Exception {
        data.constructData();
        Question question = data.getQuestionByID(id);
        Question myQuestion;

        if (question.getLanguage() == "French") {
            myQuestion = new Question(question.getPhrasing(), "English", question.getAuthor(), question.getType(), question.getCorrect());
        } else if (question.getLanguage() == "English") {
            myQuestion = new Question(question.getPhrasing(), "French", question.getAuthor(), question.getType(), question.getCorrect());
        } else {
            throw new Exception("Invalid language exception! Please choose between 'French' or 'Enlish'");
        }

        data.addQuestion(myQuestion);
        questionService.persist(myQuestion);
        return QuestionText.QuestionToJson(myQuestion);
    }
}