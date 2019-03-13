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
import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

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
	public Person findPerson(String email){
		return em.find(Person.class, email);
	}

	@Transactional
	public Person findPersonByEmail(String email) throws Exception {
		TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.email = :email", Person.class);
		query.setParameter("email", email);
		List<Person> results = query.getResultList();
		if (results.isEmpty())
			throw new Exception("Aucun utilisateur correspondant.");
		return results.get(0);

	}
	
	
	@Transactional
	public boolean deletAllPerson() throws Exception{
		List<Person> query = em.createQuery(helper.selectAll(Person.class)).getResultList();
		 
		
		for (Person p : query){
		if(questionService.findPersonQuestion(p).isEmpty()){
			Person PersonDeleted =em.merge(p);
			em.remove(PersonDeleted);
		}else{
		    questionService.deleteAllPersonQuestion(p);
		    Person PersonDeleted =em.merge(p);
			em.remove(PersonDeleted);
		}
		}
		if (!query.isEmpty())
			return false;
		return true;
	}
	
	@Transactional
	public boolean deletPersonById(int id) throws Exception{
		Person personResult = em.find(Person.class, id);
		if (personResult == null)
			return false;
		em.merge(personResult);
		em.remove(personResult);
		return true;
	}

}
