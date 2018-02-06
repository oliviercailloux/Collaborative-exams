package io.github.oliviercailloux.collaborative_exams.controller;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.SynchronizationType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.helper.QueryHelper;



@Path("hello")
public class HelloQuestion {

	@PersistenceUnit
	private EntityManagerFactory emFactory;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		
		final EntityManager em = emFactory.createEntityManager();	
		
		return "Hello World";
	}
}