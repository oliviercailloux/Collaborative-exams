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
import io.github.oliviercailloux.collaborative_exams.model.entity.question.DifficultyType;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import java.util.ArrayList;

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

        for (Answer a : question.getAnswers()) {
            em.persist(a);

        }

    }

    @Transactional
    public Question findQuestion(int id) throws Exception {
        Question questionResult = em.find(Question.class, id);
        if (questionResult == null) {
            throw new Exception("Aucune question correspondante.");
        }

        return questionResult;
    }

    /**
     *
     * @param question
     * @return la liste des reponse difficile selon la question
     *
     */
    @Transactional
    public List<Question> findQuestionByHardDifficulty(Question question) {
        return getHardDifficultyQuestion((List<Question>) em.createNamedQuery("Question.findAll").getResultList());
    }

    /**
     *
     * @param question
     * @return la liste des reponse de difficulté moyenne selon la question
     *
     */
    @Transactional
    public List<Question> findQuestionByMediumDifficulty(Question question) {
        return getMediumDifficultyQuestion((List<Question>) em.createNamedQuery("Question.findAll").getResultList());
    }

    /**
     *
     * @param question
     * @return la liste des reponse facile selon la question
     *
     */
    @Transactional
    public List<Question> findQuestionByEasyDifficulty(Question question) {
        return getEasyDifficultyQuestion((List<Question>) em.createNamedQuery("Question.findAll").getResultList());
    }

    /**
     *
     * @param listeQuestionIn
     * @return listeAnswersOut
     *
     * Retourne la liste des question de difficulté moyenne, avec ratio >= 0.66
     * ratio = getCountCorrect / getCountSelect
     */
    public List<Question> getHardDifficultyQuestion(List<Question> listeQuestionIn) {
        List<Question> listeQuestionOut = new ArrayList<>();
        double ratio;
        for (Question question : listeQuestionIn) {
            ratio = (double) question.getCountCorrectAnswer() / (double) question.getCountSelection();
            if (ratio >= 0.66) {
                question.setDifficultyType(DifficultyType.Easy);
                listeQuestionOut.add(question);
            }
        }
        return listeQuestionOut;
    }

    /**
     *
     * @param listeQuestionIn
     * @return listeAnswersOut
     *
     * Retourne la liste des reponses de difficulté moyenne, avec 0.66 > ratio
     * && ratio >= 0.33 ratio = getCountCorrect / getCountSelect
     */
    public List<Question> getMediumDifficultyQuestion(List<Question> listeQuestionIn) {
        List<Question> listeQuestionOut = new ArrayList<>();
        double ratio;
        for (Question question : listeQuestionIn) {
            ratio = (double) question.getCountCorrectAnswer() / (double) question.getCountSelection();
            if (0.66 > ratio && ratio >= 0.33) {
                question.setDifficultyType(DifficultyType.Easy);
                listeQuestionOut.add(question);
            }
        }
        return listeQuestionOut;
    }

    /**
     *
     * @param listeQuestionIn
     * @return listeAnswers
     *
     * Retourne la liste des reponses de difficulté facile, avec 0.33 > ratio
     * ratio = getCountCorrect / getCountSelect
     */
    public List<Question> getEasyDifficultyQuestion(List<Question> listeQuestionIn) {
        List<Question> listeQuestionOut = new ArrayList<>();
        double ratio;
        for (Question question : listeQuestionIn) {
            System.out.println("question ====== " + question.getPhrasing().getPhrasing());
            System.out.println("getCountCorrectAnswer ==== "+ question.getCountCorrectAnswer());
            System.out.println("getCountSelection ==== "+ question.getCountSelection());
            ratio = (double) question.getCountCorrectAnswer() / (double) question.getCountSelection();
            System.out.println("ratio ===== "+ ratio);
            if (0.33 > ratio) {
                question.setDifficultyType(DifficultyType.Easy);
                listeQuestionOut.add(question);
            }
        }
        return listeQuestionOut;
    }
}
