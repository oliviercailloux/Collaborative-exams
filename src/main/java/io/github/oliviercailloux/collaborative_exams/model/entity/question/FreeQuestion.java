package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.util.Objects;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import io.github.oliviercailloux.collaborative_exams.model.entity.Language;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

/**
 * This Class represents FreeQuestion
 * <p>
 * 
 * 
 *
 * @author Amine BOUKALA
 */
@JsonbPropertyOrder({ "id", "author", "phrasing", "language", "type", "answer" })

@XmlRootElement
@Entity
public class FreeQuestion implements IQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	private  int id;

	/**
	 * represent the phrasing of the question
	 * <p>
	 * Not <code>null</code>.
	 */
	@XmlElement
	@Column(nullable = false)
	private  String phrasing;

	/**
	 * represent the language of Question
	 * <p>
	 * <p>
	 * Not <code>null</code>, may be empty.
	 */
	@XmlElement
	@Column(nullable = false)
	private  Language language;

	/**
	 * respresente the Author
	 *
	 * @see Person Not <code>null</code>.
	 */

	@ManyToOne
	@XmlElement(name = "author")
	private  Person author;


	/**
	 * Respresent the type of question
	 *
	 * @see QuestionType Not <code>null</code>.
	 */
	@XmlElement
	@Column(nullable = false)
	private  QuestionType type;



	@XmlElement
	@Column(nullable = false)
	private  Answer answer;
	

	/**
	 * return Question Free
	 *
	 * @param phrasing phrasing of question
	 * @param language Language of question
	 * @param author   Person how represente the author of question
	 * @param type     Type of question
	 * @Param answer the answer of the free question
	 */
	public FreeQuestion(int id, String phrasing, Language language, Person author, QuestionType type, Answer answer)
	{
		this.id = Objects.requireNonNull(id);
		this.phrasing = Objects.requireNonNull(phrasing);
		this.language = Objects.requireNonNull(language);
		this.author = Objects.requireNonNull(author);
		this.type = Objects.requireNonNull(type);
		this.answer = Objects.requireNonNull(answer);
	}
	

	/**
	 * Returns this FreeQuestion’s id.
	 *
	 * @return not <code>null</code>.
	 */

	public int getId() {
		return id;
	}

	/**
	 * Returns this Question’s language.
	 *
	 * @return String not null.
	 */

	public Language getLanguage() {
		return language;
	}

	/**
	 * Returns this Question’s author.
	 *
	 * @return Person not null and immuable.
	 */

	public Person getAuthor() {
		return author;
	}

	/**
	 * Returns this Question’s type.
	 *
	 * @return String not null.
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
	 * @return String can be null, if the Question is TF/ YN / QCM
	 */
	public Answer getAnswer() {

		if (this.answer == null)
			return null;

		return this.answer;
	}
	
	public FreeQuestion() {}





}
