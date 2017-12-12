package helper;

import javax.json.Json;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

import model.entity.question.Question;

public class QuestionText {

	public static Question JsonToQuestion(String json) throws Exception {

		try (Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true))) {
			Question q = new Question();
			q = jsonb.fromJson(json, q.getClass());
			return q;

		}

	}

	public static String QuestionToJson(Question question) throws Exception {

		try (Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true))) {
			return jsonb.toJson(question);
		}
	}
}
