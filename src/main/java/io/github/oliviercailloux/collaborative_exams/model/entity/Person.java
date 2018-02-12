package io.github.oliviercailloux.collaborative_exams.model.entity;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

/**
 * This Class represent a Person
 * 
 * @author badga & Sid
 *
 */
@JsonbPropertyOrder({ "id", "email" })
@Entity
@XmlRootElement(name = "author")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	/**
	 * Represents the person's Id
	 */
	private int id;

	@OneToMany(mappedBy = "author")
	private List<Question> questions;
	/**
	 * Represents the person's email
	 */
	private String email;
	
	public Person() {

	}
	
	/**
	 * Construct a Person
	 * 
	 * @param id
	 * @param email
	 */
	public Person(String email) {
		this.email = email;
		}

	/**
	 * Returns this Person Id.
	 *
	 * @return Int not <code>null</code>.
	 */
	
	public int getId() {
		return id;
	}

	/**
	 * Returns this Person email.
	 *
	 * @return String not <code>null</code>.
	 */
	
	public String getEmail() {
		return email;
	}

	/**
	 * Sets this Person email.
	 *
	 * @param email
	 *            String not <code>null</code>
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Add the @param to this persons's questions list
	 * @param question
	 */
	public void addQuestion(Question question) {
		if (question != null)
		{
			initiateQuestionsList();
			this.questions.add(question);
		}
	}
	
	@Transient
	/**
	 * 
	 * @return this person's questions list
	 */
	public List<Question> getQuestions() {
		return this.questions;
	}
	
	/**
	 * Initiate this person's questions list
	 */
	public void initiateQuestionsList() {
		if (this.questions == null)
		this.questions = new ArrayList<Question>();
	}

}
