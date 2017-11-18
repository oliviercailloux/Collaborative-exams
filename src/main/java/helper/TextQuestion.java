package helper;

import model.entity.question.Question;

public class TextQuestion {

	public static Question getQuestion(int id) {
		
		String filename = id + ".json";
		String json = JSONhelper.fileToString(filename);
		Question question = (Question) JSONhelper.JsonToObject(json, Question.class);
		return question;
	}
	
	public static void writeQuestion(Question question) {
		
		String filename = question.getId() + ".json";
		JSONhelper.stringToFile(JSONhelper.ObjectToJson(question), filename);
	}	
	
}
