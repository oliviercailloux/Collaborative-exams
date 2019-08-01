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
import io.github.oliviercailloux.collaborative_exams.model.entity.PersonTag;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@RequestScoped
public class PersonTagService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private QueryHelper helper;

	@Transactional
	public List<PersonTag> getAll() {
		return em.createQuery(helper.selectAll(PersonTag.class)).getResultList();
	}

	@Transactional
	public void persist(PersonTag personTag) {
		em.persist(personTag);
	}

	@Transactional
	public PersonTag findPersonTag(int id) throws Exception {

		PersonTag personTag = em.find(PersonTag.class, id);
		if (personTag == null)
			throw new Exception("Don't find PersonTag for id :  " + id);
		return personTag;
	}

	@Transactional
	public PersonTag findPersonTag(Question idQuestion) throws Exception {
		TypedQuery<PersonTag> query = em.createQuery("SELECT p FROM PersonTag p WHERE p.question = :idQuestion",
				PersonTag.class);
		query.setParameter("idQuestion", idQuestion);
		List<PersonTag> result = query.getResultList();
		if (result.isEmpty())
			return null;
		else
			return result.get(0);

	}

	@Transactional
	public PersonTag findPersonTag(Question idQuestion, Person idAuthor) {
		TypedQuery<PersonTag> query = em.createQuery(
				"SELECT p FROM PersonTag p WHERE p.question = :idQuestion and p.author = :idAuthor", PersonTag.class);

		query.setParameter("idAuthor", idAuthor);
		query.setParameter("idQuestion", idQuestion);
		List<PersonTag> result = query.getResultList();

		if (result.isEmpty())
			return null;
		else
			return result.get(0);
	}

}
