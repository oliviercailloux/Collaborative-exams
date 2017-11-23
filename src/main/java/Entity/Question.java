package Entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Question implements MultipleChoiceQuestion {

	private int idQuestion;
	private String phrasing;
	private String language;
	private Author author;
	private List<Answer> answers;

 	private static Map<Integer, Question> allQuestions = new HashMap<Integer, Question>();

	public static Question getQuestionBy(int id) {
		if (!allQuestions.isEmpty()) {
			if (allQuestions.containsKey(id)) {
				return allQuestions.get(id);
			}
		}
		return null;
	}

	public static void saveQuestion(Question question) {
		if (!allQuestions.containsKey(question.getIdQuestion())) {
			allQuestions.put(question.getIdQuestion(), question);
		}
		// else afficher une erreur ?
	}

	public static boolean exist(int idQuestion) {
		return allQuestions.containsKey(idQuestion);
	}

	public Question() {
		answers = new LinkedList<>();
	}

	public Question(int idQuestion, String phrasing, String language, Author author, List<Answer> answers) {

		this.idQuestion = idQuestion;
		this.phrasing = phrasing;
		this.language = language;
		this.author = author;

		this.answers = new LinkedList<>();
		for (Answer answer : answers) {
			this.answers.add(answer);
		}
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Question addAnswer(Answer answer) {
		this.answers.add(answer);

		return this;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPhrasing() {
		return phrasing;
	}

	public void setPhrasing(String phrasing) {
		this.phrasing = phrasing;
	}

	@Override
	public String toString() {
		return "[Question id: " + this.getIdQuestion() + ", Phrasing:" + this.getPhrasing() + ", Language:"
				+ this.getLanguage() + ", Author: " + this.getAuthor() + ", Answers:" + this.getAnswers() + "]";
	}

}
