package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.beans.Transient;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private boolean correct;

	private String text;

	@ManyToOne
	private Question question;

	public Answer() {

	}

	public Answer(String text, boolean correct) {
		this.correct = correct;
		this.text = text;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getId() {
		return id;
	}

	@Transient
	public Question getQuestion() {
		return this.question;
	}

}
