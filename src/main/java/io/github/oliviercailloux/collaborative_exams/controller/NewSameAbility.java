package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.SameAbilityService;
import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;

@Path("NewSameAbility")
public class NewSameAbility {
	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;

	@Inject
	private SameAbilityService sameAbilityService;

	@POST
	public void newSameAbility(@QueryParam("idQuestion1") Integer idQuestion1,
			@QueryParam("idQuestion2") Integer idQuestion2, @QueryParam("idAuthor") Integer idAuthor) throws Exception {

		if (questionService.findQuestion(idQuestion1) == null)
			throw new NotFoundException("the question id :" + idQuestion1 + " doesn't exist.");

		if (questionService.findQuestion(idQuestion2) == null)
			throw new NotFoundException("the question id :" + idQuestion2 + " doesn't exist.");

		if (personService.findPerson(idAuthor) == null)
			throw new NotFoundException("the author id :" + idAuthor + " doesn't exist.");

		if (idQuestion1 == idQuestion2) {
			throw new IllegalArgumentException("You indicated the same id Question for both of the questions.");

		}

		/*
		 * On positionne d'abord la question avec l'id le plus petit afin d'optimiser la
		 * recherche
		 */
		int q1, q2;

		q1 = Math.min(idQuestion1, idQuestion2);
		q2 = Math.max(idQuestion1, idQuestion2);

		SameAbility s = new SameAbility(questionService.findQuestion(q1), questionService.findQuestion(q2),
				personService.findPerson(idAuthor));

		/*
		 * Test if the object is already in database
		 */

		if (sameAbilityService.isSameAbility(personService.findPerson(idAuthor),
				questionService.findQuestion(idQuestion1), questionService.findQuestion(idQuestion2))) {
			throw new IllegalArgumentException("This relation already exists.");
		}
		sameAbilityService.persist(s);

	}

}
