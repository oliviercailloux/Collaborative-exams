package io.github.oliviercailloux.collaborative_exams;

import io.github.oliviercailloux.collaborative_exams.Service.ExamService;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Exam;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.MCQuestion;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Before;

/**
 *
 * @author Khaled
 */
public class ExamServiceTest extends TestCase {

    int countParticipat = 0;

    Exam exam = new Exam("exam1");

    MCQuestion question1 = new MCQuestion("question1", new ArrayList<>());
    MCQuestion question2 = new MCQuestion("question2", new ArrayList<>());
    MCQuestion question3 = new MCQuestion("question3", new ArrayList<>());

    Answer answer11 = new Answer("answer11", true);
    Answer answer12 = new Answer("answer12", false);
    Answer answer13 = new Answer("answer13", false);

    Answer answer21 = new Answer("answer21", true);
    Answer answer22 = new Answer("answer22", false);
    Answer answer23 = new Answer("answer23", false);

    Answer answer31 = new Answer("answer31", true);
    Answer answer32 = new Answer("answer32", false);
    Answer answer33 = new Answer("answer33", false);

    @Before
    @Override
    public void setUp() {

        question1.getAnswers().add(answer11);
        question1.getAnswers().add(answer12);
        question1.getAnswers().add(answer13);

        question2.getAnswers().add(answer21);
        question2.getAnswers().add(answer22);
        question2.getAnswers().add(answer23);

        question3.getAnswers().add(answer31);
        question3.getAnswers().add(answer32);
        question3.getAnswers().add(answer33);

        exam.getListeQuestions().add(question1);
        exam.getListeQuestions().add(question2);
        exam.getListeQuestions().add(question3);

        ExamService examService = new ExamService();
        examService.setTestMode(true);
        examService.updateCountParticipeForEachAnswer(exam);
    }

    public void testIncrementCountParticipate() {
        for (MCQuestion question : exam.getListeQuestions()) {
            for (Answer answer : question.getPropositions()) {
                countParticipat = countParticipat + answer.getStats().getCountParticipat();
            }
        }
        assertEquals(countParticipat, 6);
    }
}
