package io.github.oliviercailloux.collaborative_exams.controller.Servlets;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

@Path("NewQuestion")
public class NewQuestion {

	@Path("new")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addBook(MultivaluedMap<String, String> form) throws Exception {

		int id = Integer.valueOf(form.getFirst("id"));
		String type = form.getFirst("type");
		int idAuthor = Integer.valueOf(form.getFirst("idAuthor"));
		String phrasing = form.getFirst("phrasing");
		String language = form.getFirst("language");

		QuestionType qType = null;

		switch (type) {
		case "TF":
			qType = QuestionType.TF;
			break;

		case "YN":
			qType = QuestionType.YN;
			break;

		case "Free":
			qType = QuestionType.Free;
			break;

		case "QCM":
			qType = QuestionType.QCM;
			break;

		default:
			break;

		}

		Question question;

		if (type.equals("YN") || type.equals("TF")) {

			Boolean isCorrect = Boolean.valueOf(form.getFirst("isCorrect"));
			question = new Question(phrasing, language, data.getAuthorByID(idAuthor), qType, isCorrect);

		} else if (type.equals("free")) {
			String freeAnswer = form.getFirst("freeAnswer");
			List<Answer> answers = new ArrayList<>();
			answers.add(new Answer(freeAnswer, true));

			question = new Question(phrasing, language, data.getAuthorByID(idAuthor), qType, answers);
		} else {
			// multipleChoiceQuestion creation
			List<String> AnswersText = form.get("answersText");
			List<String> answersCorrect = form.get("answersCorrect");
			List<Answer> answers = new ArrayList<>();

			int i = 0;

			for (String s : AnswersText) {
				answers.add(new Answer(s, Boolean.valueOf(answersCorrect.get(i))));
				i++;
			}
			question = new Question(phrasing, language, data.getAuthorByID(idAuthor), qType, answers);

		}
		data.addQuestion(question);

		return QuestionText.QuestionToJson(question);
	}

	

}
