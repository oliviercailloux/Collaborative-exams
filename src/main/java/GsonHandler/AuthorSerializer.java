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


import Entity.Author;

public class AuthorSerializer implements JsonSerializer{

	@Override
	public JsonElement serialize(Object authorSrc, Type typeOfsrc, JsonSerializationContext context) {
		Author author = (Author) authorSrc;
		
		final JsonObject jsonObject = new JsonObject();
	    jsonObject.addProperty("idAuthor", author.getIdAuthor());
	    jsonObject.addProperty("nameAuthor", author.getNameAuthor());
	    jsonObject.addProperty("email", author.getEmail());
	
		return jsonObject;
	}
	
	public static void main(final String[] args) throws IOException {
	    // Configure GSON
	    final GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.registerTypeAdapter(Author.class, new AuthorSerializer());
	    gsonBuilder.setPrettyPrinting();
	    final Gson gson = gsonBuilder.create();

	    final Author myAuthor = new Author();
	    myAuthor.setIdAuthor(2);
	    myAuthor.setNameAuthor("Badga 3ebdelmajid lampoule");
	    myAuthor.setEmail("Badga@gmail.com");
	

	    // Format to JSON
	    final String json = gson.toJson(myAuthor);
	    System.out.println(json);
	  }


}
