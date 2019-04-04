package io.github.oliviercailloux.collaborative_exams;

import io.github.oliviercailloux.collaborative_exams.Service.AnswerQuestionService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.AnswerQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Exam;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.MCQuestion;
import junit.framework.TestCase;

/**
 *
 * @author Khaled
 */
public class AnswerQuestionServiceTest extends TestCase {
// tester si le fait qu'une personne reponde faux provoque une incrementation dans le countSelect

    AnswerQuestion answerQuestion
            = new AnswerQuestion("",
                    new Person("a@a.a"),
                    new Exam(),
                    new MCQuestion(),
                    new Answer("answer", false));

    public void testIncrementCountSelect() {
        new AnswerQuestionService().updateCountAnswerSelect(answerQuestion);
        assertEquals(answerQuestion.getAnswer().getStats().getCountSelect(), 1);
    }
}
