package io.github.oliviercailloux.collaborative_exams.model.entity.entityManager;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class MyEntityManagerFactory {

	private EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("collaborativeExams");
	
	public MyEntityManagerFactory() {
		
	}

	public EntityManagerFactory getEmFactory() {
		return emFactory;
	}
	/*
	@PostConstruct
    public void afterCreate() {
        System.out.println("EntityManagerFactory Created !!!!");
    }*/
	
	public EntityManager createEntityManager() {
		return emFactory.createEntityManager();
	}
	
}
