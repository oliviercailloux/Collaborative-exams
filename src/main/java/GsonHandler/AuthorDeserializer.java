package GsonHandler;

import java.lang.reflect.Type;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import Entity.Author;

public class AuthorDeserializer implements JsonDeserializer{

	@Override
	public Object deserialize(JsonElement json, Type typeOft, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();

	    final Author author = new Author();
	    author.setIdAuthor(jsonObject.get("idAuthor").getAsInt());
	    author.setNameAuthor(jsonObject.get("nameAuthor").getAsString());
	    author.setEmail(jsonObject.get("email").getAsString());
	    
	    return author;
	
	}

}
