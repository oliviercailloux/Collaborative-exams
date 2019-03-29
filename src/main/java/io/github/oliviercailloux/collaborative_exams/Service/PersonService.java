package io.github.oliviercailloux.collaborative_exams.Service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

@RequestScoped
public class PersonService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Inject
	private QuestionService questionService;

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

	@Transactional
	public Person findPerson(String email) {
		return em.find(Person.class, email);
	}

	@Transactional
	public Person findPersonByEmail(String email) {
		TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email", Person.class);
		query.setParameter("email", email);
		List<Person> results = query.getResultList();
		if (results.isEmpty())
			return null;
		return results.get(0);

	}

	@Transactional
	public void deletAllPersons() throws Exception {
		List<Person> person = em.createQuery(helper.selectAll(Person.class)).getResultList();
		for (Person p : person) {
			Person PersonDeleted = em.merge(p);
			em.remove(PersonDeleted);
		}
	}

	@Transactional
	public void deletPersonById(int id) throws Exception {
		Person personResult = em.find(Person.class, id);
		em.merge(personResult);
		em.remove(personResult);
	}

}
