package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@RequestScoped
public class QuestionService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private QueryHelper helper;

    @Transactional
    public List<Question> getAll() {
        return em.createQuery(helper.selectAll(Question.class)).getResultList();
    }

    @Transactional
    public void persist(Question question) {
        if (null == em.find(Person.class, question.getAuthor().getId())) {
            em.persist(question.getAuthor());
        }
        em.persist(question);

        for (Answer a : question.getAnswers()
                ) {
            em.persist(a);

        }

    }

    @Transactional
    public Question findQuestion(int id) throws Exception {
        Question questionResult = em.find(Question.class, id);
        if (questionResult == null)
            throw new Exception("Aucune question correspondante.");

        return questionResult;
    }

}
