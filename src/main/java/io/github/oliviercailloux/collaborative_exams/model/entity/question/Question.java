package io.github.oliviercailloux.collaborative_exams.model.entity.question;


import java.util.*;

import javax.json.bind.annotation.JsonbPropertyOrder;

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
@JsonbPropertyOrder({"id","author","language","phrasing","type"})
public class Question {

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
	 * @see Person
	 *   Not <code>null</code>, may be empty.
	 */
	protected Person author;

	
	/**
	 * 
	 * @see QuestionType
	 *   Not <code>null</code>, may be empty.
	 */
	private QuestionType type;
	
	
	/**
	 * count how many questions in the application
	 *  Not <code>null</code>, may be empty.
	 */
    public static int questionCount;	
    
	/**
	 * Returns a new Question.
	 *   Not <code>null</code>, may be empty.
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
     *           not <code>null</code>.
     *           
     */
	public Question(int id,String phrasing,String language,Person author,QuestionType type)
	{
		this.id = Objects.requireNonNull(id);
		this.phrasing = Objects.requireNonNull(language);
		this.author = Objects.requireNonNull(author);
		this.type = Objects.requireNonNull(type);
		
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
	 * Returns this Question’s phrasing.
	 *
	 * @return String not <code>null</code>.
	 */
	public String getPhrasing() {
		return phrasing;
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
	 * Returns this Question’s language.
	 *
	 * @return String not <code>null</code>.
	 */
	public void setLanguage(String language) {
		this.language = language;
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


}
