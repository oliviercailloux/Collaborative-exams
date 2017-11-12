package GsonHandler;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import Entity.Answer;

public class AnswerSerialization implements JsonSerializer<Answer>, JsonDeserializer<Answer> {

	@Override
	public JsonElement serialize(Answer answerSrc, Type typeOfsrc, JsonSerializationContext context) {

		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("idAnswer", answerSrc.getIdAnswer());
		jsonObject.addProperty("text", answerSrc.getText());
		jsonObject.addProperty("correct", answerSrc.isCorrect());

		return jsonObject;
	}

	@Override
	public Answer deserialize(JsonElement json, Type typeOft, JsonDeserializationContext context)
			throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();

		final Answer answer = new Answer();
		answer.setIdAnswer(jsonObject.get("idAnswer").getAsInt());
		answer.setText(jsonObject.get("text").getAsString());
		answer.setCorrect(jsonObject.get("correct").getAsBoolean());

		return answer;

	}

	public static void main(final String[] args) throws IOException {
		// Configure GSON
		final GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Answer.class, new AnswerSerialization());
		gsonBuilder.setPrettyPrinting();
		final Gson gson = gsonBuilder.create();

		final Answer myAnswer = new Answer();
		myAnswer.setIdAnswer(2);
		myAnswer.setText("Oui");
		myAnswer.setCorrect(true);

		// Format to JSON
		final String json = gson.toJson(myAnswer);
		System.out.println(json);
	}

}
