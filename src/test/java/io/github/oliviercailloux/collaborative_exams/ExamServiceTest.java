/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.oliviercailloux.collaborative_exams;

import io.github.oliviercailloux.collaborative_exams.Service.ExamService;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Exam;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionPhrasing;
import junit.framework.TestCase;
import org.junit.Before;

/**
 *
 * @author Khaled
 */
public class ExamServiceTest extends TestCase {

    int countCorrecteAnswers = 0;

    Exam exam = new Exam("exam1", 20);

    Question question1 = new Question(new QuestionPhrasing("question1"));
    Question question2 = new Question(new QuestionPhrasing("question2"));
    Question question3 = new Question(new QuestionPhrasing("question3"));

    @Before
    @Override
    public void setUp() {
        exam.getListeQuestions().add(question1);
        exam.getListeQuestions().add(question2);
        exam.getListeQuestions().add(question3);

        new ExamService().updateCountSelectForEachQuestion(exam);
    }

    /**
     * Tester lorsque le prof selectionne une question le compteur countSelect
     * augmente du nombre de candidats, ceci se verifi pour chaque question
     */
    public void testIncrementCountSelection() {
        for (Question question : exam.getListeQuestions()) {
            assertEquals(question.getCountSelection(), 20);
        }
    }
}
