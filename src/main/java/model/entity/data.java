package model.entity;

import model.entity.question.Question;

public class data {
	
	
	public static Question questions = new Question(1,"abouche");
	
	
	public Question getQuestions()
	{
		return questions;
	}
	
	public static Question getQuestion(int pos)
	{
		return questions;
	}

}
