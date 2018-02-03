package io.github.oliviercailloux.collaborative_exams.controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.model.entity.entityManager.MyEntityManagerFactory;



@Path("hello")
public class HelloQuestion {
	
	
	private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("collaborativeExams");
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		
		EntityManager em = emFactory.createEntityManager();
		
		return "Hello World";
	}
}