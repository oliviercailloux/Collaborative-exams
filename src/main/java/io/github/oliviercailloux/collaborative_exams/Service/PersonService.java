package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

@RequestScoped
public class PersonService {
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Transactional
	public List<Person> getAll() {
		return em.createQuery(helper.selectAll(Person.class)).getResultList();
	}

	@Transactional
	public void persist(Person person) {
		em.persist(person);
	}
	
	@Transactional
	public Person findPerson(int id) {
		return em.find(Person.class, id);
	}

}
