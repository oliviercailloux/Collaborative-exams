package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.util.Objects;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import io.github.oliviercailloux.collaborative_exams.model.entity.Language;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;




/**
 * This Class represents Yes/No question, it extends Multiple Choice Question but has only 2 answers.
 * <p>
 *
 * @author Amine BOUKALA
 */
@JsonbPropertyOrder({ "id", "author", "phrasing", "language", "type", "answers" })

@XmlRootElement
@Entity
public class YesNoQuestion extends MCQuestion {

	public YesNoQuestion(String phrasing, Language language, Person author, QuestionType type, Answer rightAnswer, Answer wrongAnswer)
	 {
		
		super(phrasing, language, author, type);
		rightAnswer.setQuestionIfNull(this);
		wrongAnswer.setQuestionIfNull(this);
		this.answers = Objects.requireNonNull(answers);
	}
	
	

	public Answer getRightAnswer()
	{
		
		
		return this.answers.get(0);
		
		
	}
	public YesNoQuestion() {}
	
	
}
