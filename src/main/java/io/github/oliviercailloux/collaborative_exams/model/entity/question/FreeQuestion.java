package io.github.oliviercailloux.collaborative_exams.model.entity.question;


import java.util.Objects;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

/**
 * FreeQuestion is the class represente Question whith Free response.  
 * 
 * FreeQuestion is immuable
 * 
 * @author badga
 *
 */
public class FreeQuestion extends Question {
	

	/**
	 * represente the answer of the question
	 * 
	 * @see Answer
	 */
	private Answer answer;

    /**
     * 
     * return new MultipleChoiceQuestion
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
	public FreeQuestion(int id,String phrasing,String language,Person author,Answer answers)
	{
		this.id = Objects.requireNonNull(id);
		this.phrasing = Objects.requireNonNull(language);
	    this.author = Objects.requireNonNull(author);
		this.answer = Objects.requireNonNull(answers);
		
	}
	
	
	/**
	 * Returns this Question’s answer.
	 *
	 * @return List of answer not <code>null</code> and unmodifiable.
	 * 
	 */
	public Answer getAnswers() {
		return this.answer;
		 
	}
	


}
