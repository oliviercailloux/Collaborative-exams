package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
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
		em.persist(question);
	}
	
	@Transactional
	public void findQuestion(Person question, int id) {
		em.find(question.getClass(), id);
	}

}
