package io.github.oliviercailloux.collaborative_exams.helper;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

public class QuestionAdapter implements JsonbAdapter<Question, JsonObject> {
	private PersonAdapter personAdapter = new PersonAdapter();

	private AnswerAdapter answerAdapter = new AnswerAdapter();

	@Override
	public JsonObject adaptToJson(Question obj) throws Exception {
		return null;
	}

	@Override
	public Question adaptFromJson(JsonObject obj) throws Exception {
		Question question = new Question();
		String phrasing = obj.getString("phrasing");
		String language = obj.getString("language");
		String type = obj.getString("type");

		Person author = new Person();
		if (!obj.isNull("author")) {
			author = personAdapter.adaptFromJson(obj.getJsonObject("author"));
		} else {
			author = new Person();
		}

		Boolean isCorrect;
		QuestionType qType;

		switch (type) {
		case "TF":
			qType = QuestionType.TF;
			isCorrect = obj.getBoolean("correct");
			question = new Question(phrasing, language, author, qType, isCorrect);
			break;

		case "YN":
			qType = QuestionType.YN;
			isCorrect = obj.getBoolean("correct");
			question = new Question(phrasing, language, author, qType, isCorrect);
			break;

		case "Free":
			qType = QuestionType.Free;
			JsonArray answer = obj.getJsonArray("answers");
			question = new Question(phrasing, language, author, qType,
					answerAdapter.adaptFromJson(answer.getJsonObject(0)));
			break;

		case "QCM":
			qType = QuestionType.QCM;

			JsonArray Janswers = obj.getJsonArray("answers");
			List<Answer> answers = new ArrayList<Answer>();
			for (int i = 0; i < Janswers.size(); i++)
				answers.add(answerAdapter.adaptFromJson(Janswers.getJsonObject(i)));

			question = new Question(phrasing, language, author, qType, answers);
			break;

		default:
			break;

		}

		return question;
	}
}
