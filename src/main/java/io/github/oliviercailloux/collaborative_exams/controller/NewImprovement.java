package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import io.github.oliviercailloux.collaborative_exams.Service.ImprovementService;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.SameAbilityService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Improvement;
import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;

/**
 * JAX-RS Servlet that allows to create and to persist a new improvement
 * relation between two questions for an author
 * 
 * @author Sid
 *
 */
@Path("NewImprovement")
public class NewImprovement {

	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;

	@Inject
	private ImprovementService ImprovementService;

	@Inject
	private SameAbilityService SameAbilityService;

	/**
	 * 
	 * @param form
	 *            contains the question id of the two questions and the author id
	 * @return the Id of the new improvement relation
	 * @throws Exception
	 *             if one / both of the questions is / are null or the author is
	 *             null or the two questions don't have a sameAbility relation
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public int newImprovement(MultivaluedMap<String, String> form) throws Exception {

		int idQuestion1 = Integer.valueOf(form.getFirst("idQuestion1"));

		int idQuestion2 = Integer.valueOf(form.getFirst("idQuestion2"));

		int idAuthor = Integer.valueOf(form.getFirst("idAuthor"));

		if (questionService.findQuestion(idQuestion1) == null)
			throw new Exception("the question id :" + idQuestion1 + "is null");

		if (questionService.findQuestion(idQuestion2) == null)
			throw new Exception("the question id :" + idQuestion2 + "is null");

		if (personService.findPerson(idAuthor) == null)
			throw new Exception("the author id :" + idAuthor + "is null");

		SameAbility sameability = SameAbilityService.isSameAbility(personService.findPerson(idAuthor),
				questionService.findQuestion(idQuestion1), questionService.findQuestion(idQuestion2));
		if (sameability == null)
			throw new Exception("this questions don't have the same ability");

		Improvement improvement = new Improvement(questionService.findQuestion(idQuestion1),
				questionService.findQuestion(idQuestion2), personService.findPerson(idAuthor));
		ImprovementService.persist(improvement);

		return improvement.getId();

	}

}
