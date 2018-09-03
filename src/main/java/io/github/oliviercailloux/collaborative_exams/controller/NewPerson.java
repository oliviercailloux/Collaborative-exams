package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

/**
 * Jax-RS servlet that allows to add a new Person and persist it
 * 
 * @author Sid
 *
 */
@Path("NewAuthor")
public class NewPerson {

	@Inject
	private PersonService personService;

	/**
	 * 
	 * @param email : contains the email sent by the form
	 * @return : the object person in Json
	 * @throws Exception
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addBook(@FormParam("email") String email) throws Exception {

		Person author = new Person(email);
		personService.persist(author);

		return QuestionText.ObjectToJson(Person.class, author);
	}

}
