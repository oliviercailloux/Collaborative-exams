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
        em.persist(updateCountAnswerSelect(answerQuestion));
    }

    public AnswerQuestion updateCountAnswerSelect(AnswerQuestion answerQuestion) {
        if (!answerQuestion.getAnswer().isCorrect()) {
            answerQuestion.getAnswer().getStats().setCountSelect(answerQuestion.getAnswer().getStats().getCountSelect() + 1);
            // this verification is only for test (to not start the server and update the database)
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
