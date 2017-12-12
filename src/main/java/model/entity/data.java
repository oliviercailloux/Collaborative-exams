package model.entity;

import java.util.ArrayList;
import java.util.List;

import model.entity.question.Question;

public class data {

	public static List<Question> questions = new ArrayList<Question>() {
		{
			add(new Question(1, "abouche"));
			add(new Question(2, "ahechoune"));
		}
	};
	
	public static int questionCount = 3;

	public List<Question> getQuestions() {
		return questions;
	}

	public static Question getQuestion(int pos) {
		return questions.get(pos);
	}

	public void addQuestion(Question question) {
		questions.add(question);
		questionCount++;
	}

}
