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

    private boolean testMode = false;

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
        em.persist(updateCountParticipeForEachAnswer(exam));
    }

    @Transactional
    public Exam findExam(int id) {
        return em.find(Exam.class, id);
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public void constructExam(Exam exam, List<Integer> listIdQuestion) {
        List<Question> listQuestion = em.createNamedQuery("Question.findByListIdQuestion").setParameter("listIdQuestion", listIdQuestion).getResultList();
        for (Question q : listQuestion) {
            exam.getListeQuestions().add(q);
        }
    }

    public Exam updateCountParticipeForEachAnswer(Exam exam) {
        for (Question question : exam.getListeQuestions()) {
            for (Answer answer : question.getAnswers()) {
                if (!answer.isCorrect()) {
                    answer.getStats().setCountParticipat(answer.getStats().getCountParticipat() + 1);
                    // this verification is only for test (to not start the server and update the database)
                    if (!testMode) {
                        em.persist(answer);
                    }
                }
            }
        }
        return exam;
    }
}
