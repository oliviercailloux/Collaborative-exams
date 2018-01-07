package model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import helper.QuestionText;
import model.entity.question.Answer;
import model.entity.question.Question;

public class data {

	public static int questionCount = 0;

	private static List<Person> authors = new ArrayList<Person>() {
		{
			add(new Person(1, "Dupon@gmail.com"));
			add(new Person(2, "toto@gmail.com"));
			add(new Person(3, "laforet@gmail.com"));
		}

	};

	private static List<Question> questions = new ArrayList<>();

	public static void constructData() {
		Question question1 = new Question(1, "Que mange la baleine ?", "french");
		List<Answer> answers1 = new ArrayList<>();

		Answer answer1 = new Answer();
		Answer answer2 = new Answer();

		answer1.setText("krill / crevettes");
		answer1.setCorrect(true);

		answer2.setText("Allgues / plantes marines");
		answer2.setCorrect(false);

		answers1.add(answer1);
		answers1.add(answer2);

		question1.setAuthor(getAuthorByID(1));
		question1.setAnswers(answers1);
		addQuestion(question1);

		Answer answer3 = new Answer();
		Answer answer4 = new Answer();

		Question question2 = new Question(2, "Que mangent les chiens ?", "french");
		List<Answer> answers2 = new ArrayList<>();

		answer3.setText("de la viande");
		answer3.setCorrect(true);

		answer4.setText("des croquettes");
		answer4.setCorrect(true);

		answers2.add(answer3);
		answers2.add(answer4);

		question1.setAuthor(getAuthorByID(2));

		addQuestion(question2);
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public static Question getQuestionByID(int id) {

		ListIterator<Question> itQuestion = questions.listIterator();
		while (itQuestion.hasNext()) {
			Question q = itQuestion.next();
			if (q.getId() == id)
				return q;
		}
		return new Question();
	}

	public static void addQuestion(Question question) {
		questions.add(question);
		questionCount++;
	}

	public static Person getAuthorByID(int idAuthor) {
		ListIterator<Person> itAuthor = authors.listIterator();
		while (itAuthor.hasNext()) {
			Person author = itAuthor.next();
			if (author.getId() == idAuthor)
				return author;

		}
		return new Person();
	}

	public static int getQuestionCount() {
		return questionCount;
	}
}
