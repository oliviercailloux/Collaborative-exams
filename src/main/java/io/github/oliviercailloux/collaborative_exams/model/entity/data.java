package io.github.oliviercailloux.collaborative_exams.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.MultipleChoiceQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.SameAbility;

public class data {

	public static int questionCount = 0;
	public static int sameAbilityCount = 0;
	
	private static List<SameAbility>  sameAbility  = new ArrayList<>();

	private static List<Person> authors = new ArrayList<Person>() {
		/**
		 * 
		 */
		{
			add(new Person(1, "Dupon@gmail.com"));
			add(new Person(2, "toto@gmail.com"));
			add(new Person(3, "laforet@gmail.com"));
		}

	};

	private static List<Question> questions = new ArrayList<>();

	public static void constructData() {
		
		Question question1 = new Question();
		String phrasing = "Que mange la baleine ?";
		String language = "French";
		List<Answer> answers1 = new ArrayList<>();

		Answer answer1 = new Answer();
		Answer answer2 = new Answer();

		answer1.setText("krill / crevettes");
		answer1.setCorrect(true);

		answer2.setText("Allgues / plantes marines");
		answer2.setCorrect(false);

		answers1.add(answer1);
		answers1.add(answer2);

		Person author = getAuthorByID(1);
		MultipleChoiceQuestion mquestion1 = new MultipleChoiceQuestion(1,phrasing,language,author,answers1);
		
		addQuestion(mquestion1);
		
		Answer answer3 = new Answer();
		Answer answer4 = new Answer();

		Question question2 = new Question();
		String phrasing2 = "Que mangent les chiens ?";
		
		List<Answer> answers2 = new ArrayList<>();

		answer3.setText("de la viande");
		answer3.setCorrect(true);

		answer4.setText("des croquettes");
		answer4.setCorrect(true);

		answers2.add(answer3);
		answers2.add(answer4);

		Person author2 = getAuthorByID(2);
		
		MultipleChoiceQuestion mquestion2 = new MultipleChoiceQuestion(2,phrasing2,language,author2,answers2);

		questions.add(mquestion2);
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
	

	public static int getSameAbilityCount() {
		return sameAbilityCount;
	}
	
	
	
	public static void addSameAbility(SameAbility s)
	{
		sameAbility.add(s);
		sameAbilityCount++;	
	}
	
	public static boolean isSameAbilities(int q1,int q2)
	{
		for (SameAbility s : data.sameAbility) {
			
			if(s.isSameAbility( data.getQuestionByID(q1) , data.getQuestionByID(q2) ) )
				return true;
				
		}
		
		return false;
		
	}
	
	public static void main(String[] args) throws Exception{
			constructData();
			
			System.out.println(QuestionText.QuestionToJson(questions.get(0)));
		} 
	
	
}
