package GsonHandler;

import java.lang.reflect.Type;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import Entity.Question;

public class QuestionDeserializer implements JsonDeserializer{

	@Override
	public Object deserialize(JsonElement json, Type typeOft, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();

	    final Question question = new Question();
	    question.setIdQuestion(jsonObject.get("idQuestion").getAsInt());
	    //question.setAuthor(jsonObject.get("author").getAsString());
	    
	    question.setLanguage(jsonObject.get("language").getAsString());
	    question.setPhrasing(jsonObject.get("phrasing").getAsString());
	    
	    
	   // question.addAnswer()
	    
	    return question;
	
	}

}
