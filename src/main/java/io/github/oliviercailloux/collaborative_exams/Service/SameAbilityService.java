package io.github.oliviercailloux.collaborative_exams.Service;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Improvement;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class SameAbilityService {
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Transactional
	public List<SameAbility> getAll() {
		return em.createQuery(helper.selectAll(SameAbility.class)).getResultList();
	}

	@Transactional
	public void persist(SameAbility same) {

		em.persist(same);

	}
	
	@Transactional
	public SameAbility findSameAbility(int id) {
		return em.find(SameAbility.class, id);
	}


	@Transactional
	public SameAbility isSameAbility(Question idQuestion1, Question idQuestion2) {
		TypedQuery<SameAbility> query = em.createQuery(
				"SELECT s FROM SameAbility s WHERE ((s.question1 = :idQuestion1 and s.question2 = :idQuestion2) or (s.question1 = :idQuestion2 and s.question2 = :idQuestion1)   )", SameAbility.class);

		query.setParameter("idQuestion1", idQuestion1);
		query.setParameter("idQuestion2", idQuestion2);

		List<SameAbility> results = query.getResultList();
		if (results.isEmpty()) return null;
		else  return results.get(0);
	}
}
