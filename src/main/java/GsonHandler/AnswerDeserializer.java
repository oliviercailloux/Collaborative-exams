package GsonHandler;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import Entity.Answer;




public class AnswerDeserializer implements JsonDeserializer{

	@Override
	public Object deserialize(JsonElement json, Type typeOft, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();

	    final Answer answer = new Answer();
	    answer.setIdAnswer(jsonObject.get("idAnswer").getAsInt());
	    //answer.setAuthor(jsonObject.get("author").getAsString());
	    
	    answer.setText(jsonObject.get("text").getAsString());
	    answer.setCorrect(jsonObject.get("phrasing").getAsBoolean());
	    
	    return answer;
	
	}
	
	
	

}
