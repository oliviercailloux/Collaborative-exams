package io.github.oliviercailloux.collaborative_exams.helper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

import io.github.oliviercailloux.collaborative_exams.Service.AnswerService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Language;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.FreeQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.IQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.MCQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.TrueFalseQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.YesNoQuestion;

public class IQuestionAdapter implements JsonbAdapter<IQuestion, JsonObject> {
	
	@Inject
	private AnswerService answerService;
	
	private PersonAdapter personAdapter = new PersonAdapter();

	private AnswerAdapter answerAdapter = new AnswerAdapter();


	@Override
	public IQuestion adaptFromJson(JsonObject obj) throws Exception {
		IQuestion question = null;
		String phrasing = obj.getString("phrasing");
		String language = obj.getString("language");
		String type = obj.getString("type");

		Person author = new Person();
		if (!obj.isNull("author")) {
			author = personAdapter.adaptFromJson(obj.getJsonObject("author"));
		} else {
			author = new Person();
		}

		QuestionType qType;

		switch (type) {
		case "TF":
			 qType = QuestionType.TF;
			 int idAnswer1 = obj.getInt("idAnswer1");
			 int idAnswer2 = obj.getInt("idAnswer2");
			 Answer answer1 = answerService.findAnswer(idAnswer1);
			 Answer answer2 = answerService.findAnswer(idAnswer2);
			 question = new TrueFalseQuestion(phrasing, Language.valueOf(language), author, qType, answer1, answer2);			
			
			break;

		case "YN":
			 qType = QuestionType.YN;
			 idAnswer1 = obj.getInt("idAnswer1");
			 idAnswer2 = obj.getInt("idAnswer2");
			 answer1 = answerService.findAnswer(idAnswer1);
			 answer2 = answerService.findAnswer(idAnswer2);
			 question = new YesNoQuestion(phrasing, Language.valueOf(language), author, qType, answer1, answer2);			
			break;

		case "Free":
			qType = QuestionType.Free;
			
			qType = QuestionType.Free;
			int idAnswer = obj.getInt("idAnswer");
			Answer answer = answerService.findAnswer(idAnswer);
			question = new FreeQuestion(phrasing,Language.valueOf(language), author, qType, answer);
		
			break;

		case "QCM":
			qType = QuestionType.QCM;
			
			JsonArray Janswers = obj.getJsonArray("answers");
			List<Answer> answers = new ArrayList<Answer>();
			for (int i = 0; i < Janswers.size(); i++)
				answers.add(answerAdapter.adaptFromJson(Janswers.getJsonObject(i)));

			question = new MCQuestion(phrasing, Language.valueOf(language), author, qType, answers);
			break;

		default:
			break;

		}

		return question;
	}


	@Override
	public JsonObject adaptToJson(IQuestion obj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
