package io.github.oliviercailloux.collaborative_exams.model.entity;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@JsonbPropertyOrder({ "idPersonTag", "question", "tag" })
@Entity
@XmlRootElement(name = "author")
public class PersonTag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	private int idPersonTag;

	@OneToOne
	@XmlElement(name = "question")
	private Question question;

	@OneToOne
	@XmlElement(name = "author")
	private Person author;

	@XmlElement(name = "tag")
	private String tag;

	public PersonTag(Question question, Person author, String tag) {
		this.question = question;
		this.author = author;
		this.tag = tag;
	}

	public PersonTag() {
	}

	public int getIdPersonTag() {
		return idPersonTag;
	}

	public Question getQuestion() {
		return question;
	}

	public Person getAuthor() {
		return author;
	}

	public String getTag() {
		return tag;
	}

	public void setIdPersonTag(int idPersonTag) {
		this.idPersonTag = idPersonTag;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
