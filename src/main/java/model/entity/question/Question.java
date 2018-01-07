package model.entity.question;

import java.util.List;
import javax.json.bind.annotation.JsonbPropertyOrder;
import model.entity.Person;
import model.entity.question.*;

@JsonbPropertyOrder({ "id", "phrasing", "language", "author", "answers", "type" })
public class Question {

	private int id;
	private String phrasing;
	private String language;
	private Person author;
	private List<Answer> answers;
	private QuestionType type;
	public static int questionCount;

	public Question() {

	}

	public Question(Question question) {
		this.id = question.id;
		this.phrasing = question.phrasing;
		this.author = question.author;
		this.language = question.language;
		this.type = question.type;
		this.answers = question.answers;

	}

	public Question(int id, String phrasing, String language) {
		this.id = id;
		this.phrasing = phrasing;
		this.language = language; // à changer ?

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhrasing() {
		return phrasing;
	}

	public void setPhrasing(String phrasing) {
		this.phrasing = phrasing;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public model.entity.Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

}
