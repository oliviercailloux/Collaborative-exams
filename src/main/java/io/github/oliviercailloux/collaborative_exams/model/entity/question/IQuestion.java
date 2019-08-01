package io.github.oliviercailloux.collaborative_exams.model.entity.question;


import java.util.List;

import io.github.oliviercailloux.collaborative_exams.model.entity.Language;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

/**
 * This Class represents Question
 * <p>
 * Question is immutable
 *
 * @author Amine BOUKALA
 */
public interface IQuestion {
	
	public int getId();
	/**
	 * Returns this Question’s language.
	 *
	 * @return Language not null.
	 */
	public Language getLanguage();
	/**
	 * Returns this Question’s author.
	 *
	 * @return Person not null
	 */
	public Person getAuthor();
	/**
	 * Returns this Question’s type.
	 *
	 * @return String not null.
	 */
	public QuestionType getType();
	/**
	 * Returns this Question’s phrasing.
	 *
	 * @return String not <code>null</code>.
	 */
	public String getPhrasing();
	
	public List<Answer> getAnswers() ;

}
