package io.github.oliviercailloux.collaborative_exams.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@Entity
@XmlRootElement(name = "RelationSameAbility")
public class SameAbility {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	private int idSameAbility;

	@XmlElement(name = "question")
	private Question question1;

	@XmlElement(name = "question")
	private Question question2;

	@XmlElement(name = "author")
	private Person author;

	public SameAbility() {
	}

	public SameAbility(Question q1, Question q2, Person author) {
		this.question1 = q1;
		this.question2 = q2;
		this.author = author;
	}

	public boolean isSameAbility(Question q1, Question q2) {
		if (question1.equals(q1) && question2.equals(q2)) {
			return true;
		} else if (question1.equals(q2) && question2.equals(q1)) {
			return true;
		}

		return false;
	}

}
