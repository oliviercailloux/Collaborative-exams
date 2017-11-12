package GsonHandler;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

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
import Entity.Author;
import Entity.Question;

public class QuestionSerialization implements JsonSerializer<Question>, JsonDeserializer<Question> {

	@Override
	public JsonElement serialize(Question questionSrc, Type typeOfsrc, JsonSerializationContext context) {

		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("idQuestion", questionSrc.getIdQuestion());
		jsonObject.addProperty("language", questionSrc.getLanguage());
		jsonObject.addProperty("phrasing", questionSrc.getPhrasing());

		final JsonElement jsonAuthor = context.serialize(questionSrc.getAuthor());
		jsonObject.add("author", jsonAuthor);

		final JsonElement jsonAnswers = context.serialize(questionSrc.getAnswers());
		jsonObject.add("answers", jsonAnswers);

		return jsonObject;
	}

	@Override
	public Question deserialize(JsonElement json, Type typeOft, JsonDeserializationContext context)
			throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();

		final Question question = new Question();
		question.setIdQuestion(jsonObject.get("idQuestion").getAsInt());
		question.setLanguage(jsonObject.get("language").getAsString());
		question.setPhrasing(jsonObject.get("phrasing").getAsString());

		question.setAuthor(context.deserialize(jsonObject.get("author"), Author.class));

		final JsonArray jsonAnswersArray = jsonObject.get("answers").getAsJsonArray();
		final List<Answer> answers = new LinkedList<>();
		for (JsonElement answer : jsonAnswersArray) {
			answers.add(context.deserialize(answer, Answer.class));
		}

		question.setAnswers(answers);

		return question;

	}

}
