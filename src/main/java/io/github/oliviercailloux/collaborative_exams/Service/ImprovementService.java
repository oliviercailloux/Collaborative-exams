package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Improvement;


@RequestScoped
public class ImprovementService {
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Transactional
	public List<Improvement> getAll() {
		return em.createQuery(helper.selectAll(Improvement.class)).getResultList();
	}

	@Transactional
	public void persist(Improvement improvement) {
		em.persist(improvement);
	}
	
	@Transactional
	public Improvement findImprovement(int id) {
		return em.find(Improvement.class, id);
	}
}
