package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.util.*;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

/**
 * Question is the class which represente Question
 * 
 * Question is immuable
 * 
 * @author badga
 *
 */
@JsonbPropertyOrder({"id", "author","phrasing", "language", "type", "isCorrect", "answers"})
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected int id;

	/**
	 * 
	 * 
	 */
	protected String phrasing;

	/**
	 * respresente the language of Question
	 * 
	 * 
	 * Not <code>null</code>, may be empty.
	 * 
	 */
	protected String language;

	/**
	 * respresente the Author
	 * 
	 * @see Person Not <code>null</code>, may be empty.
	 */
	protected Person author;

	/**
	 * respresente if the response whas correct in T/F or Y/N question
	 * 
	 * Not <code>null</code>, may be empty.
	 */
	private boolean isCorrect;

	/**
	 * 
	 * @see QuestionType Not <code>null</code>, may be empty.
	 */
	private QuestionType type;

	
	/**
	 * Represent the possible answers of a MQC Question
	 */
	private List<Answer> answers;
	/**
	 * count how many questions in the application Not <code>null</code>, may be
	 * empty.
	 */
	public static int questionCount;

	/**
	 * Returns a new Question. Not <code>null</code>, may be empty.
	 */
	public Question() {

	}

	/**
	 * 
	 * return new Question
	 * 
	 * @param id
	 * @param phrasing
	 * @param language
	 * @param author
	 * @param type
	 * 
	 *            not <code>null</code>.
	 * 
	 */
	public Question(int id, String phrasing, String language, Person author, QuestionType type, boolean isCorrect) {
		this.id = Objects.requireNonNull(id);
		this.phrasing = Objects.requireNonNull(phrasing);
		this.language = Objects.requireNonNull(language);
		this.author = Objects.requireNonNull(author);
		this.type = Objects.requireNonNull(type);
		this.isCorrect = Objects.requireNonNull(isCorrect);
	}
	
	/**
	 * 
	 * return new MCQ Question
	 * 
	 * @param id
	 * @param phrasing
	 * @param language
	 * @param author
	 * @param type
	 * 
	 *            not <code>null</code>.
	 * 
	 */
	public Question(int id, String phrasing, String language, Person author, QuestionType type, List<Answer> answers) {
		this.id = Objects.requireNonNull(id);
		this.phrasing = Objects.requireNonNull(phrasing);
		this.language = Objects.requireNonNull(language);
		this.author = Objects.requireNonNull(author);
		this.type = Objects.requireNonNull(type);
		this.answers = Objects.requireNonNull(answers);
	}
	
	

	/**
	 * Returns this Question’s id.
	 *
	 * @return not <code>null</code>.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns this Question’s language.
	 *
	 * @return String not <code>null</code>.
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Returns this Question’s author.
	 *
	 * @return Person not <code>null</code> and immuable.
	 */
	public Person getAuthor() {
		return author;
	}

	/**
	 * Returns this Question’s type.
	 *
	 * @return String not <code>null</code>.
	 */
	public QuestionType getType() {
		return type;
	}

	/**
	 * Returns this Question’s phrasing.
	 *
	 * @return String not <code>null</code>.
	 */
	public String getPhrasing() {
		return this.phrasing;
	}
	
	/**
	 * Returns this Question’s answers.
	 *
	 * @return String can be <code>null</code>. if the Question is TF/ YN / QCM
	 */
	public List<Answer> getAnswers(){
		return this.answers;
	}

	/**
	 * 
	 * @Return boolean
	 */
	public boolean getCorrect() {
		return isCorrect;
	}

	
	public boolean equalz(Object obj) {
		
		if (obj== this) return true;
		if (obj != null && (obj.getClass().equals(this.getClass()))) {

			Question question = (Question) obj;
			return (this.phrasing.equals(question.getPhrasing()) && this.author.equals(question.getAuthor())
					&& (this.id == question.getId()) && (this.getCorrect() == question.getCorrect())
					&& (this.getLanguage().equals(question.getLanguage())));

		}
		return false;
	}
	 
}
