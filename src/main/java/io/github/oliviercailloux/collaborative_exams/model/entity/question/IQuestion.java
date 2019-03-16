package io.github.oliviercailloux.collaborative_exams.model.entity.question;


import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

/**
 * This Class represents Question
 * <p>
 * Question is immuable
 *
 * @author Amine BOUKALA
 */

public interface IQuestion {
	
	public int getId();

	/**
	 * Returns this Question’s language.
	 *
	 * @return String not null.
	 */

	public String getLanguage();
	/**
	 * Returns this Question’s author.
	 *
	 * @return Person not null and immuable.
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

	/**
	 * Returns this Question’s answers.
	 *
	 * @return String can be null, if the Question is TF/ YN / QCM
	 */

	public boolean equals(Question question);
}
