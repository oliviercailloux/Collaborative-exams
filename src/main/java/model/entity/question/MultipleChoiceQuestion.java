package model.entity.question;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import model.entity.Person;

/**
 * MultipleChoiceQuestion is the class represente Question whith Multiple response.  
 * 
 * MultipleChoiceQuestion is immuable
 * 
 * @author badga
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
		this.phrasing = Objects.requireNonNull(language);
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
