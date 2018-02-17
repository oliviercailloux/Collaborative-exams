package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

/**
 * Jax-RS Servlet that allows to change a true/false question to a yes/no question
 */
@Path("ChangeResponseTypeYN")
public class ChangeResponseTypeYN {

    @Inject
    private QuestionService questionService;

    @Inject
    private PersonService personService;


    /**
     * @param form                  that contains the idQuestion and the new authorId that can be null if cookie is set
     * @param newAuthorIdFromCookie contains the new authorId if the cookie is set
     * @return the new Id of the question after modification
     * @throws Exception if the type of the question is already YN or the type cannot be changed to YN or IdQuestion is invalid
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestion(MultivaluedMap<String, String> form, @CookieParam("authorId") String newAuthorIdFromCookie) throws Exception {

        int idQuestion = Integer.valueOf(form.getFirst("idQuestion"));
        int newAuthorId;

        if (newAuthorIdFromCookie == null)
            newAuthorId = Integer.valueOf(form.getFirst("newAuthorId"));
        else
            newAuthorId = Integer.valueOf(newAuthorIdFromCookie);

        Question question = questionService.findQuestion(idQuestion);
        Person newAuthor = personService.findPerson(newAuthorId);

        Question modifiedQuestion;

        QuestionType questionType = question.getType();

        if (questionType == QuestionType.TF) {
            modifiedQuestion = new Question(question.getPhrasing(), question.getLanguage(), newAuthor, QuestionType.YN,
                    question.getCorrect());

        } else if (questionType == QuestionType.YN) {
            throw new Exception("Question type is already YN ! ");
        } else throw new Exception("This type of question can't change");

        questionService.persist(modifiedQuestion);
        return String.valueOf(modifiedQuestion.getId());

    }
}
	