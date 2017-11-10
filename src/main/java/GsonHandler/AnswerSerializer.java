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

public class AnswerSerializer implements JsonSerializer{

	@Override
	public JsonElement serialize(Object answerSrc, Type typeOfsrc, JsonSerializationContext context) {
		Answer answer = (Answer) answerSrc;
		
		final JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("idAnswer", answer.getIdAnswer());
	    jsonObject.addProperty("text", answer.getText());
	    jsonObject.addProperty("corrct", answer.isCorrect());
	
		return jsonObject;
	}
	
	public static void main(final String[] args) throws IOException {
	    // Configure GSON
	    final GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.registerTypeAdapter(Answer.class, new AnswerSerializer());
	    gsonBuilder.setPrettyPrinting();
	    final Gson gson = gsonBuilder.create();

	    final Answer javaPuzzlers = new Answer();
	    javaPuzzlers.setIdAnswer(2);
	    javaPuzzlers.setText("Oui");
	    javaPuzzlers.setCorrect(true);
	

	    // Format to JSON
	    final String json = gson.toJson(javaPuzzlers);
	    System.out.println(json);
	  }


}
