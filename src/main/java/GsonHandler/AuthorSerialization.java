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

import Entity.Author;

public class AuthorSerialization implements JsonSerializer<Author>, JsonDeserializer<Author> {

	@Override
	public JsonElement serialize(Author authorSrc, Type typeOfsrc, JsonSerializationContext context) {

		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("idAuthor", authorSrc.getIdAuthor());
		jsonObject.addProperty("nameAuthor", authorSrc.getNameAuthor());
		jsonObject.addProperty("email", authorSrc.getEmail());

		return jsonObject;
	}

	@Override
	public Author deserialize(JsonElement json, Type typeOft, JsonDeserializationContext context)
			throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();

		final Author author = new Author();
		author.setIdAuthor(jsonObject.get("idAuthor").getAsInt());
		author.setNameAuthor(jsonObject.get("nameAuthor").getAsString());
		author.setEmail(jsonObject.get("email").getAsString());

		return author;

	}

}
