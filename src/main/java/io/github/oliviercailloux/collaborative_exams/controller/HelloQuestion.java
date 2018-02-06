package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;



@Path("hello")
public class HelloQuestion {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private PersonService personService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		
		Person person = new Person("toto@test.com");
		personService.persist(person);
		
		return "Hello World" + personService.getAll().get(0).getId() + " email : " + personService.getAll().get(0).getEmail();
	}
}