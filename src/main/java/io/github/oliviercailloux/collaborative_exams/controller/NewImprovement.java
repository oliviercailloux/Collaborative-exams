package io.github.oliviercailloux.collaborative_exams.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import io.github.oliviercailloux.collaborative_exams.model.entity.Improvement;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;


@Path("Improvement")
public class NewImprovement {
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public int newImprovement(MultivaluedMap<String, String> form) throws Exception {
		
		int idQuestion1  = Integer.valueOf(form.getFirst("idQuestion1"));
		int idQuestion2  = Integer.valueOf(form.getFirst("idQuestion2"));
		
		int idAuthor  = Integer.valueOf(form.getFirst("idAuthor"));

		Improvement i = new Improvement(data.getAuthorByID(idAuthor),data.getQuestionByID(idQuestion1),data.getQuestionByID(idQuestion2));
		
		data.addImprovement(i);	
		return data.sameAbilityCount;
		
	}

}