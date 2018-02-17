package io.github.oliviercailloux.collaborative_exams.controller;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionAdapter;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;

@Path("NewQuestionJson")
public class NewQuestionJson {

    @Inject
    QuestionService questionService;

    @Inject
    QuestionAdapter questionAdapter;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addBook(JsonObject questionJson) throws Exception {

        Question question = questionAdapter.adaptFromJson(questionJson);
        questionService.persist(question);

        return QuestionText.QuestionToJson(question);
    }

}
