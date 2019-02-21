/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.oliviercailloux.collaborative_exams.Service;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Exam;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Khaled
 */
public class ExamService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private QueryHelper helper;

    @Transactional
    public List<Exam> getAll() {
        return em.createQuery(helper.selectAll(Exam.class)).getResultList();
    }

    @Transactional
    public void persist(Exam exam) {
        em.persist(updateCountSelectForEachQuestion(exam));
    }

    @Transactional
    public void findExam(int id) {
        em.find(Exam.class, id);
    }

    public Exam updateCountSelectForEachQuestion(Exam exam) {
        for (Question question : exam.getListeQuestions()) {
            // Losqu'un prof selectionne une question, cette derniere incremonte la variable Count selection du nombre de candidat
            question.setCountSelection(question.getCountSelection() + exam.getNumberCandidate());
        }
        return exam;
    }
}
