/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.oliviercailloux.collaborative_exams;

import io.github.oliviercailloux.collaborative_exams.Service.AnswerService;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author Khaled
 */
public class AnswerServiceTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AnswerServiceTest(String testName) {
        super(testName);
    }

    public void testEasyDifficulty() {
        AnswerService service = new AnswerService();
        List<Answer> listeAnswers = new ArrayList<>();

        listeAnswers.add(new Answer("Reponse1", false, 10, 3));
        listeAnswers.add(new Answer("Reponse2", false, 10, 5));
        listeAnswers.add(new Answer("Reponse3", false, 10, 7));

        service.getEasyDifficultyAnswer(listeAnswers);

        assertEquals(service.getEasyDifficultyAnswer(listeAnswers).size(), 1);
        assertEquals(service.getEasyDifficultyAnswer(listeAnswers).get(0).getText(), "Reponse1");
    }

    public void testMediumDifficulty() {
        AnswerService service = new AnswerService();
        List<Answer> listeAnswers = new ArrayList<>();

        listeAnswers.add(new Answer("Reponse1", false, 10, 3));
        listeAnswers.add(new Answer("Reponse2", false, 10, 5));
        listeAnswers.add(new Answer("Reponse3", false, 10, 7));

        service.getMediumDifficultyAnswer(listeAnswers);

        assertEquals(service.getMediumDifficultyAnswer(listeAnswers).size(), 1);
        assertEquals(service.getMediumDifficultyAnswer(listeAnswers).get(0).getText(), "Reponse2");
    }

    public void testHardDifficulty() {
        AnswerService service = new AnswerService();
        List<Answer> listeAnswers = new ArrayList<>();

        listeAnswers.add(new Answer("Reponse1", false, 10, 3));
        listeAnswers.add(new Answer("Reponse2", false, 10, 5));
        listeAnswers.add(new Answer("Reponse3", false, 10, 7));

        service.getHardDifficultyAnswer(listeAnswers);

        assertEquals(service.getHardDifficultyAnswer(listeAnswers).size(), 1);
        assertEquals(service.getHardDifficultyAnswer(listeAnswers).get(0).getText(), "Reponse3");
    }
}
