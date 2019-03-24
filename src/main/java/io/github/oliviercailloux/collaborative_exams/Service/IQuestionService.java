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
import io.github.oliviercailloux.collaborative_exams.model.entity.question.IQuestion;

@RequestScoped
public class IQuestionService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Transactional
	public List<IQuestion> getAll() {
		return em.createQuery(helper.selectAll(IQuestion.class)).getResultList();
	}

	@Transactional
	public void persist(IQuestion question) {
		if (null == em.find(Person.class, question.getAuthor().getId())) {
			em.persist(question.getAuthor());
		}
		em.persist(question);

		for (Answer a : question.getAnswers()) {
			em.persist(a);

		}

	}

	@Transactional
	public IQuestion findQuestion(int id) throws Exception {
		IQuestion questionResult = em.find(IQuestion.class, id);
		if (questionResult == null)
			throw new Exception("Aucune question correspondante.");

		return questionResult;
	}

}
