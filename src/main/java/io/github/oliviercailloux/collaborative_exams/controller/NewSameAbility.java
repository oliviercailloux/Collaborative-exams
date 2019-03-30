package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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
	@Consumes(MediaType.TEXT_PLAIN)
	public void newSameAbility(@QueryParam("idQuestion1") Integer idQuestion1, @QueryParam("idQuestion2") Integer idQuestion2, @QueryParam("idAuthor") Integer idAuthor) throws Exception {

		if (questionService.findQuestion(idQuestion1) == null)
			throw new Exception("the question id :" + idQuestion1 + "is null.");

		if (questionService.findQuestion(idQuestion2) == null)
			throw new Exception("the question id :" + idQuestion2 + "is null.");

		if (personService.findPerson(idAuthor) == null)
			throw new Exception("the author id :" + idAuthor + "is null.");

		if (idQuestion1 == idQuestion2) {
			throw new Exception("You indicated the same id Question for both of the questions.");

		}
		
		/*
		 * On positionne d'abord la question avec l'id le plus petit afin d'optimiser la recherche
		 * 
		 */
		
		SameAbility s;
		
		if(idQuestion1 < idQuestion2)
		{
			s = new SameAbility(questionService.findQuestion(idQuestion1),
			questionService.findQuestion(idQuestion2), personService.findPerson(idAuthor));
		}
		else
		{
			s = new SameAbility(questionService.findQuestion(idQuestion1),
			questionService.findQuestion(idQuestion2), personService.findPerson(idAuthor));
		}
		
		

	
		/*
		 *  Test if the object is already in database
		 */
		
		List <SameAbility> listSameAbility = sameAbilityService.getAll();
		
		for (SameAbility sameAbility : listSameAbility)
		{
			if(sameAbility.equals(s))
				throw new Exception("This relation already exists.");
		}

		sameAbilityService.persist(s);


	}

}
