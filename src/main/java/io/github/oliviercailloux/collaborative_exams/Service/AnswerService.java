package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;

@RequestScoped
public class AnswerService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private QueryHelper helper;

    @Transactional
    public List<Answer> getAll() {
        return em.createQuery(helper.selectAll(Answer.class)).getResultList();
    }

    @Transactional
    public void persist(Answer Answer) {
        em.persist(Answer);
    }

    @Transactional
    public void findAnswer(int id) {
        em.find(Answer.class, id);
    }

}
