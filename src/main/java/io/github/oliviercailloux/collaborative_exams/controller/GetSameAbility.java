package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.SameAbilityService;

/**
 * 
 * 
 * @author Amine
 *
 */
@Path("GetSameAbility")
public class GetSameAbility {

	@Inject
	SameAbilityService sameAbilityService;

	@Inject
	QuestionService questionService;

	@Inject
	PersonService personService;

	@Path("/getSameAbility")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean getSameAbility(@QueryParam("idAuthor") int idAuthor, @QueryParam("idQuestion1") int idQuestion1,
			@QueryParam("idQuestion2") int idQuestion2) throws Exception {
		if (idQuestion1 == idQuestion2)
			return true;

		boolean sameAbility = sameAbilityService.isSameAbility(personService.findPerson(idAuthor),
				questionService.findQuestion(idQuestion1), questionService.findQuestion(idQuestion2));

		return sameAbility;

	}
}
