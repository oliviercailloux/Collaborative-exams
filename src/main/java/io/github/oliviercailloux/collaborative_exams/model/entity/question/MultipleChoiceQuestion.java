package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

/**
 * MultipleChoiceQuestion the class which represente Question whith Multiple responses.  
 * 
 * MultipleChoiceQuestion is immuable
 * 
 * @author Sid Ahmed
 *
 */
public class MultipleChoiceQuestion extends Question {

	/**
	 * represente the answers of the question
	 * 
	 * @see Answer
	 */
	private List<Answer> answers;

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
	public MultipleChoiceQuestion(int id,String phrasing,String language,Person author,List<Answer> answers)
	{
		this.id = Objects.requireNonNull(id);
		this.phrasing = Objects.requireNonNull(phrasing);
		this.language = Objects.requireNonNull(language);
	    this.author = Objects.requireNonNull(author);
		this.answers = Objects.requireNonNull(answers);
	}
	
	/**
	 * Returns this Question’s answers.
	 *
	 * @return List of answer not <code>null</code> and unmodifiable.
	 * 
	 */
	public List<Answer> getAnswers() {
		return Collections.unmodifiableList(this.answers);
		 
	}
	
	

}
