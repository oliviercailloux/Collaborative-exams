package io.github.oliviercailloux.collaborative_exams.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

@Entity
@XmlRootElement(name = "RelationSameAbility")
public class SameAbility {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	private int idSameAbility;

	@OneToOne
	@XmlElement(name = "question")
	private Question question1;
	
	@OneToOne
	@XmlElement(name = "question")
	private Question question2;

	@OneToOne
	@XmlElement(name = "author")
	private Person author;

	public SameAbility() {
	}

	public SameAbility(Question question1, Question question2, Person author) {

		/*
		 * this.question1.id < this.question2.id
		 */
		Question q1, q2;

		if (question1.getId() < question2.getId()) {
			q1 = question1;
			q2 = question2;
		} else {
			q1 = question2;
			q2 = question1;
		}
		this.question1 = q1;
		this.question2 = q2;
		this.author = author;
	}

	public Person getPersonAbility() {
		return this.author;
	}


	public Question getQuestion1Ability() {
		return this.question1;
	}

	public Question getQuestion2Ability() {
		return this.question2;
	}
}
