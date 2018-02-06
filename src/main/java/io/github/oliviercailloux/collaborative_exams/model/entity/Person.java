package io.github.oliviercailloux.collaborative_exams.model.entity;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

/**
 * Question is the class represente person
 * 
 * @author badga
 *
 */
@JsonbPropertyOrder({ "id", "email" })
@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@OneToMany(mappedBy = "author")
	private List<Question> questions;

	private String email;
	
	public Person() {

	}

	/**
	 * Contruct Person
	 * 
	 * @param id
	 * @param email
	 */
	public Person(String email) {
		this.email = email;
	}

	/**
	 * Returns this Person’s Id.
	 *
	 * @return Int not <code>null</code>.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns this Person’s email.
	 *
	 * @return String not <code>null</code>.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets this Person's email.
	 *
	 * @param email
	 *            String not <code>null</code>
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public void addQuestion(Question q) {
		if (q != null)
			this.questions.add(q);
	}
	
	@Transient
	public List<Question> getQuestions() {
		return this.questions;
	}

	public void initateQuestionsList() {
		this.questions = new ArrayList<>();
	}

}
