/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.oliviercailloux.collaborative_exams;

import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionPhrasing;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;

/**
 *
 * @author Khaled
 */
public class QuestionServiceTest extends TestCase {

    QuestionService service = new QuestionService();
    List<Question> listeQuestion = new ArrayList<>();

    @Before
    @Override
    public void setUp() {
        listeQuestion.add(new Question(new QuestionPhrasing("question1"), 3, 10));
        listeQuestion.add(new Question(new QuestionPhrasing("question2"), 5, 10));
        listeQuestion.add(new Question(new QuestionPhrasing("question3"), 7, 10));
    }

    /**
     * Lorsque le prof veut selectionner les question selon le ratio Ratio <
     * 0.33
     */
    public void testEasyDifficulty() {
        service.getEasyDifficultyQuestion(listeQuestion);

        assertEquals(service.getEasyDifficultyQuestion(listeQuestion).size(), 1);
        assertEquals(service.getEasyDifficultyQuestion(listeQuestion).get(0).getPhrasing().getPhrasing(), "question1");
    }

    /**
     * Lorsque le prof veut selectionner les question selon le ratio 0.33<Ratio
     * < 0.66
     */
    public void testMediumDifficulty() {
        service.getMediumDifficultyQuestion(listeQuestion);

        assertEquals(service.getMediumDifficultyQuestion(listeQuestion).size(), 1);
        assertEquals(service.getMediumDifficultyQuestion(listeQuestion).get(0).getPhrasing().getPhrasing(), "question2");
    }

    /**
     * Lorsque le prof veut selectionner les question selon le ratio Ratio >0.66
     */
    public void testHardDifficulty() {
        service.getHardDifficultyQuestion(listeQuestion);

        assertEquals(service.getHardDifficultyQuestion(listeQuestion).size(), 1);
        assertEquals(service.getHardDifficultyQuestion(listeQuestion).get(0).getPhrasing().getPhrasing(), "question3");
    }
}
