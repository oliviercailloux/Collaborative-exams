package io.github.oliviercailloux.collaborative_exams.controller;

import io.github.oliviercailloux.collaborative_exams.Service.AnswerQuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.ExamService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.AnswerQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Exam;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.IQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Khaled
 */
public class TakeExam {

    @Inject
    ExamService examService;

    @Inject
    AnswerQuestionService answerQuestionService;

    /**
     *
     * @param author
     * @param idExam
     * @param iQuestion
     * @param answer
     * @param freeAnswer
     * @return
     * @throws Exception
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String answerQuestion(Person author, int idExam, IQuestion iQuestion, Answer answer, String freeAnswer) throws Exception {
        Exam exam = examService.findExam(idExam);
        if (iQuestion.getType() == QuestionType.Free) {
            answerFree(exam, author, iQuestion, freeAnswer);
        } else {
            answerYnQcmTf(exam, author, iQuestion, answer);
        }
        return "OK!!";
    }

    private void answerFree(Exam exam, Person author, IQuestion iQuestion, String freeAnswer) {
        AnswerQuestion answerQuestion = new AnswerQuestion(author, exam, iQuestion, freeAnswer);
        answerQuestionService.persist(answerQuestion);
    }

    private void answerYnQcmTf(Exam exam, Person author, IQuestion iQuestion, Answer answer) {
        AnswerQuestion answerQuestion = new AnswerQuestion(author, exam, iQuestion, answer);
        answerQuestionService.persist(answerQuestion);
    }
}