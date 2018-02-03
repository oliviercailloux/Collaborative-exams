package io.github.oliviercailloux.collaborative_exams.helper;


import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;



public class QuestionText {

	public static Question JsonToQuestion(String json) throws Exception {

		try (Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true))) {
			Question question = jsonb.fromJson(json, Question.class);
			return question;
		}
	}

	public static String QuestionToJson(Question question) throws Exception {

		try (Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true))) {
			return jsonb.toJson(question);
		}
	}

	public static void main(String[] args) throws Exception {
		data.constructData();

		Question q = data.getQuestionByID(1);

		System.out.println(QuestionToJson(q));

	}
}
