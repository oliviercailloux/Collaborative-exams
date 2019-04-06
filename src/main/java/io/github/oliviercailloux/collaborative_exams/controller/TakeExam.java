package io.github.oliviercailloux.collaborative_exams.controller;

import io.github.oliviercailloux.collaborative_exams.Service.AnswerQuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.AnswerService;
import io.github.oliviercailloux.collaborative_exams.Service.ExamService;
import io.github.oliviercailloux.collaborative_exams.Service.IQuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.AnswerQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Exam;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.IQuestion;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Khaled
 */
public class TakeExam {

    @Inject
    PersonService personService;

    @Inject
    ExamService examService;

    @Inject
    IQuestionService iQuestionService;

    @Inject
    AnswerService answerService;

    @Inject
    AnswerQuestionService answerQuestionService;

    /**
     *
     * @param idAuthor
     * @param idExam
     * @param idIQuestion
     * @param idAnswer
     * @return
     * @throws Exception
     */
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String answerQuestion(
            @QueryParam("idAuthor") Integer idAuthor,
            @QueryParam("idExam ") Integer idExam,
            @QueryParam("iQuestion ") Integer idIQuestion,
            @QueryParam("idAnswer ") Integer idAnswer)
            throws Exception {
        Person author = personService.findPerson(idAuthor);
        Exam exam = examService.findExam(idExam);
        IQuestion iQuestion = iQuestionService.findQuestion(idIQuestion);
        Answer answer = answerService.findAnswer(idAnswer);
        AnswerQuestion answerQuestion = new AnswerQuestion(author, exam, iQuestion, answer);
        answerQuestionService.persist(answerQuestion);
        return "OK!!";
    }
}
