package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Answer is immuable, you can add the question the first time
 */
@JsonbPropertyOrder({ "id", "text", "correct" })
@Entity
@XmlRootElement(name = "answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	private int id;

	@Column(nullable = false)
	@XmlElement
	private boolean correct;

	@Column(nullable = false)
	@XmlElement
	private String text;

	//@ManyToOne
	private IQuestion question;

	public Answer() {

	}

	public Answer(String text, boolean correct) {
		this.correct = correct;
		this.text = text;
	}

	public boolean isCorrect() {
		return correct;
	}

	public String getText() {
		return text;
	}

	/**
	 * add question to answers if question is null , else throw new exception, for
	 * add
	 *
	 * @param question the question how have this answer
	 */
	public void setQuestionIfNull(IQuestion question) {
		if (this.question == null) {
			this.question = question;
		}
		
		
	}

	public int getId() {
		return id;
	}

	@JsonbTransient
	public IQuestion getQuestion() {
		return this.question;
	}

}
