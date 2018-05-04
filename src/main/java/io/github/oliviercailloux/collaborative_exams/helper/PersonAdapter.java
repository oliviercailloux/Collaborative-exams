package io.github.oliviercailloux.collaborative_exams.helper;

import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

public class PersonAdapter implements JsonbAdapter<Person, JsonObject> {
	@Override
	public JsonObject adaptToJson(Person obj) throws Exception {
		return null;
	}

	@Override
	public Person adaptFromJson(JsonObject obj) throws Exception {
		return new Person(obj.getString("email"));
	}
}
