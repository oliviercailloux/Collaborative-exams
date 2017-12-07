package model.entity;

import model.entity.question.Question;

public class data {
	
	
	public static Question[] questions = {new Question(1,"abouche"),new Question(2,"ahechoune"),new Question(3,"takhena")};
	
	
	public Question[] getQuestions()
	{
		return questions;
	}
	
	public Question getQuestion(int pos)
	{
		return questions[pos];
	}

}
