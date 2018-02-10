package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;


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
	public void persist(SameAbility SameAbility) {
		em.persist(SameAbility);
	}
	
	@Transactional
	public SameAbility findSameAbility(int id) {
		return em.find(SameAbility.class, id);
	}
}
