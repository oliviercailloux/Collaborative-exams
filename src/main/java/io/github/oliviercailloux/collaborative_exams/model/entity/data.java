package io.github.oliviercailloux.collaborative_exams.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

public class data {

	public static int questionCount = 0;
	public static int sameAbilityCount = 0;
	public static int improvementsCount = 0;

	private static List<SameAbility> sameAbility = new ArrayList<>();
	private static List<Improvement> improvements = new ArrayList<>();

	@SuppressWarnings("serial")
	private static List<Person> authors = new ArrayList<Person>() {
		/**
		 * 
		 */
		{
			add(new Person("Dupon@gmail.com"));
			add(new Person("toto@gmail.com"));
			add(new Person("laforet@gmail.com"));
		}

	};

	private static List<Question> questions = new ArrayList<>();

	public static void constructData() {

		String phrasing = "Que mange la baleine ?";
		String language = "French";
		QuestionType type = QuestionType.QCM;
		List<Answer> answers1 = new ArrayList<>();

		Answer answer1 = new Answer("krill / crevettes", true);
		Answer answer2 = new Answer("Allgues / plantes marines", false);

		answers1.add(answer1);
		answers1.add(answer2);

		Person author1 = getAuthorByID(1);
		Question mquestion1 = new Question(phrasing, language, author1, type, answers1);

		Answer answer3 = new Answer();
		Answer answer4 = new Answer();

		String phrasing2 = "Que mangent les chiens ?";

		List<Answer> answers2 = new ArrayList<>();

		answer3.setText("de la viande");
		answer3.setCorrect(true);

		answer4.setText("des croquettes");
		answer4.setCorrect(true);

		answers2.add(answer3);
		answers2.add(answer4);

		Person author2 = getAuthorByID(2);

		Question mquestion2 = new Question(phrasing2, language, author2, type, answers2);

		String phrasing3 = "2 + 2 =?= 4";

		Question question3 = new Question(phrasing3, language, author1, QuestionType.TF, true);

		addQuestion(mquestion1);
		addQuestion(mquestion2);
		addQuestion(question3);
	}

	public static List<Question> getQuestions() {
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

	public static int getSameAbilityCount() {
		return sameAbilityCount;
	}

	public static void addSameAbility(SameAbility s) {
		sameAbility.add(s);
		sameAbilityCount++;
	}

	public static void addImprovement(Improvement i) {
		improvements.add(i);
		improvementsCount++;
	}

	public static boolean isSameAbilities(int q1, int q2) {
		for (SameAbility s : data.sameAbility) {

			if (s.isSameAbility(data.getQuestionByID(q1), data.getQuestionByID(q2)))
				return true;

		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		constructData();
		/*
		 * System.out.println(QuestionText.QuestionToJson(questions.get(0)));
		 * System.out.println(QuestionText.QuestionToJson(questions.get(1)));
		 * System.out.println(QuestionText.QuestionToJson(questions.get(2)));
		 */
		Question q111 = questions.get(0);
		System.out.println(QuestionText.QuestionToJson(q111));
	}

}
