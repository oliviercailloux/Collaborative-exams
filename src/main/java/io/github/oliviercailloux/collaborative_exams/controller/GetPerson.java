package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

/**
 * @author Mohamed JAX-RS Servlet that allows you to recover people from the BDD
 */
@Path("Person")
public class GetPerson {

	@Inject
	private PersonService personService;

	/**
	 * @return
	 * @throws Exception
	 */
	@Path("/all")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getPerson() throws Exception {
		List<Person> allPerson = personService.getAll();
		String result = "";
		for (Person p : allPerson)
			result += QuestionText.ObjectToJson(Person.class, p);
		return result;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Path("/{id}")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String getPerson(@PathParam("id") int id) throws Exception {
		Person pers = personService.findPerson(id);
		return QuestionText.ObjectToJson(Person.class, pers);
	}

}
