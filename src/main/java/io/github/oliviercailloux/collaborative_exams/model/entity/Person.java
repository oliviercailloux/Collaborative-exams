package io.github.oliviercailloux.collaborative_exams.model.entity;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Question is the class represente person  
 * 
 * @author badga
 *
 */
@JsonbPropertyOrder({"id","email"})
@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	private int id;
	
	private String email;

	public Person() {

	}
	
	/**
	 * Contruct Person
	 * @param id
	 * @param email
	 */
	
	public Person(int id, String email) {
		this.id = id;
		this.email = email;
	}

	
	
	public int getId() {
		return id;
	}

	
	
	public void setId(int id) {
		this.id = id;
	}

	
	
	public String getEmail() {
		return email;
	}

	
	
	public void setEmail(String email) {
		this.email = email;
	}

}
