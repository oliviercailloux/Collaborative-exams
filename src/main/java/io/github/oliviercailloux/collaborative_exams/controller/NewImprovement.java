package io.github.oliviercailloux.collaborative_exams.controller;

import io.github.oliviercailloux.collaborative_exams.Service.ImprovementService;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.Service.SameAbilityService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Improvement;
import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;


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


	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String newImprovement(MultivaluedMap<String, String> form) throws Exception {
		
		int idQuestion1  = Integer.valueOf(form.getFirst("idQuestion1"));

		int idQuestion2  = Integer.valueOf(form.getFirst("idQuestion2"));
		
		int idAuthor  = Integer.valueOf(form.getFirst("idAuthor"));


		if(questionService.findQuestion(idQuestion1)==null)
			throw new Exception("the question id :"+idQuestion1+"is null");

			if(questionService.findQuestion(idQuestion2)==null)
				throw new Exception("the question id :"+idQuestion2+"is null");

			if(personService.findPerson(idAuthor)==null)
				throw new Exception("the author id :"+idAuthor+"is null");

		SameAbility sameability = SameAbilityService.isSameAbility(personService.findPerson(idAuthor),questionService.findQuestion(idQuestion1), questionService.findQuestion(idQuestion2));
		if (sameability == null)
			throw new Exception("this questions don't have the same ability");

		Improvement improvement = new Improvement(questionService.findQuestion(idQuestion1),questionService.findQuestion(idQuestion2),personService.findPerson(idAuthor));
		ImprovementService.persist(improvement);

		return QuestionText.ObjectToJson(Improvement.class,improvement);
		
	}

}
