package GsonHandler;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import Entity.Answer;
import Entity.Author;
import Entity.Question;

public class QuestionSerializer implements JsonSerializer{

	@Override
	public JsonElement serialize(Object questionSrc, Type typeOfsrc, JsonSerializationContext context) {
		Question question = (Question) questionSrc;
		
		final JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("idQuestion", question.getIdQuestion());
	    jsonObject.addProperty("language", question.getLanguage());
	    jsonObject.addProperty("phrasing", question.getPhrasing());
	    
	    final JsonElement jsonAuthor = context.serialize(question.getAuthor());
	    jsonObject.add("author", jsonAuthor);
	    
	    
	    final JsonElement jsonAnswers = context.serialize(question.getAnswers());
	    jsonObject.add("answers", jsonAnswers);
	    
	    
		return jsonObject;
	}
	
	public static void main(final String[] args) throws IOException {
	    // Configure GSON
	    final GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.registerTypeAdapter(Question.class, new QuestionSerializer());
	    gsonBuilder.setPrettyPrinting();
	    
	    final Gson gson = gsonBuilder.create();
	    
	    //Author:
	    final Author myAuthor = new Author();
	    myAuthor.setIdAuthor(1);
	    myAuthor.setNameAuthor("Badga 3ebdelmajid lampoule");
	    myAuthor.setEmail("Badga@gmail.com");
	    
	    //Answers:
	    
	    //Answer 1:
	    final Answer myAnswer1 = new Answer();
	    myAnswer1.setIdAnswer(1);
	    myAnswer1.setText("Oui");
	    myAnswer1.setCorrect(true);
	    
	    //Answer 2:
	    final Answer myAnswer2 = new Answer();
	    myAnswer2.setIdAnswer(2);
	    myAnswer2.setText("Non");
	    myAnswer2.setCorrect(false);

	    //Question:
	    final Question myQuestion = new Question();
	    myQuestion.setIdQuestion(2);
	    myQuestion.setAuthor(myAuthor);
	    myQuestion.addAnswer(myAnswer1);
	    myQuestion.addAnswer(myAnswer2);
	    myQuestion.setLanguage("Français");
	    myQuestion.setPhrasing("Est ce que .........?");
	    
	

	    // Format to JSON
	    final String json = gson.toJson(myQuestion);
	    System.out.println(json);
	  }


}
