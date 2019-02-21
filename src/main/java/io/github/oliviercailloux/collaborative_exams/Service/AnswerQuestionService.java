/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.oliviercailloux.collaborative_exams.Service;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.AnswerQuestion;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Khaled
 */
public class AnswerQuestionService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private QueryHelper helper;

    @Transactional
    public List<AnswerQuestion> getAll() {
        return em.createQuery(helper.selectAll(AnswerQuestion.class)).getResultList();
    }

    @Transactional
    public void persist(AnswerQuestion answerQuestion) {
        em.persist(updateCountCorrectAnswer(answerQuestion));
    }

    public AnswerQuestion updateCountCorrectAnswer(AnswerQuestion answerQuestion) {
        if (answerQuestion.getAnswer().isCorrect()) {
            // un condidat a selectionné une reponse Correcte, alors on augmante le membre de CountCorrect
            answerQuestion.getQuestion().setCountCorrectAnswer(answerQuestion.getQuestion().getCountCorrectAnswer() + 1);
            if (em != null) {
                em.persist(answerQuestion.getAnswer());
            }
        }
        return answerQuestion;
    }

    @Transactional
    public void findAnswerQuestion(int id) {
        em.find(AnswerQuestion.class, id);
    }
}
