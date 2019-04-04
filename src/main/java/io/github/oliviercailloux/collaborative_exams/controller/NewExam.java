package io.github.oliviercailloux.collaborative_exams.controller;

import io.github.oliviercailloux.collaborative_exams.Service.ExamService;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Exam;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Khaled
 */
public class NewExam {
    @Inject
    ExamService examService;

    /**
     *
     * @param nomExam
     * @param listIdQuestion separated by <;>
     * @return
     * @throws Exception
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String addExam(String nomExam, String listIdQuestion) throws Exception {
        Exam exam = new Exam(nomExam);
        examService.constructExam(exam,extractIdQuestionFromString(listIdQuestion) );
        examService.persist(exam);
        return "OK!!";
    }

    private List<Integer> extractIdQuestionFromString(String msg) {
        String[] msgSplit = msg.split(";");
        List<Integer> listId = new ArrayList<>();
        for (String msgSplit1 : msgSplit) {
            listId.add(Integer.parseInt(msgSplit1));
        }
        return listId;
    }
}
