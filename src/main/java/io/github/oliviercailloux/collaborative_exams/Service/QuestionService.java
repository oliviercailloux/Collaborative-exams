package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.IQuestion;
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

		for (Answer a : question.getAnswers()) {
			em.persist(a);

		}

	}

	@Transactional
	public Question findQuestion(int id) {
		Question question = em.find(Question.class, id);
		if (question == null) {
			throw new EntityNotFoundException(
					"Unable to find id :  " + id + ". Please, make a new request with id.");
		}
		return question;
	}

	@Transactional
	public List<Question> findPersonQuestion(Person pers) throws Exception {
		TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q WHERE q.pers = :author", Question.class);
		query.setParameter("author", pers);
		List<Question> results = query.getResultList();
		if (results.isEmpty())
			throw new Exception("No question for this Person.");
		return results;
	}
	
	@Transactional
	public List<Question> findQuestionTag(Person author, String tag) throws Exception {
		Query query = em.createQuery("SELECT q FROM PersonTag pt JOIN pt.question q WHERE pt.tag = :tag and pt.author = :author");
		query.setParameter("idAuthor", author);
		query.setParameter("tag", tag);
		List<Question> results = query.getResultList();
		if (results.isEmpty())
			throw new Exception("No question for this tag.");
		return results;
	}

	@Transactional
	public void deleteAllQuestions(Person pers) throws Exception {
		TypedQuery<Question> query = em.createQuery("SELECT q FROM Question q WHERE q.pers = :author", Question.class);
		query.setParameter("author", pers);
		List<Question> results = query.getResultList();
		for (Question q : results) {
			Question QuestionDeleted = em.merge(q);
			em.remove(QuestionDeleted);
		}

	}

	@Transactional
	public void deletAllQuestions() throws Exception {
		List<Question> question = em.createQuery(helper.selectAll(Question.class)).getResultList();

		for (Question q : question) {
			Question QuestionDeleted = em.merge(q);
			em.remove(QuestionDeleted);
		}

	}

	@Transactional
	public void deletById(int id) throws Exception {
		Question questionResult = em.find(Question.class, id);
		em.merge(questionResult);
		em.remove(questionResult);
	}

	@Transactional
	public void deletByAuthor(Person p) throws Exception {
		Question questionResult = em.find(Question.class, p.getId());
		em.merge(questionResult);
		em.remove(questionResult);

	}

}
