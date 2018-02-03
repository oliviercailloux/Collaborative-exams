package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.FreeQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.MultipleChoiceQuestion;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;


@Path("Add")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.TEXT_PLAIN)
public class Add {
	/*
	
			QuestionType qType;
			Question question;
				
			if (!type.isEmpty()) {
			if (type.equals("YN"))
					qType = QuestionType.YN;
			else 
				qType = QuestionType.TF;
			
			 question = new Question(id,phrasing,language,data.getAuthorByID(author),qType, isCorrect);
			}
			else if (!multipleChoices.isEmpty()) {
					List<Answer> answers = new ArrayList<>();  
					int i=0;
					for (String st : multipleChoices) {
						answers.add(new Answer(st, multipleChoicesAnswers.get(i)));
						i++;
					}
					 question = new MultipleChoiceQuestion(id,phrasing,language,data.getAuthorByID(author), answers);
				}
			else {
				
				question = new FreeQuestion(id,phrasing,language,data.getAuthorByID(author), new Answer(freeAnswer,true));
			}
		data.addQuestion(question);
				
		return	QuestionText.QuestionToJson(question);	
	}
	*/
	
	
	@Path("new")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addBook(MultivaluedMap<String, String> form) {
		String result = " ";
		
		String type = form.getFirst("type");
		String idAuthor = form.getFirst("idAuthor");
		String phrasing = form.getFirst("phrasing");
		String language = form.getFirst("language");

		result += idAuthor + " " + phrasing + " " + language + " " + type + " ";

		if (type.equals("YN") || type.equals("TF")) {

			String isCorrect = form.getFirst("isCorrect");
			//Question question = new Question( );
			
			
			result += isCorrect;

		} else if (type.equals("free")) {
			String freeAnswer = form.getFirst("freeAnswer");
			//Question question = new FreeQuestion();
			result += freeAnswer + " True.";

		} else {
			// multipleChoiceQuestion creation
			List<String> AnswersText = form.get("answersText");
			List<String> answersCorrect = form.get("answersCorrect");
			int i = 0;
			
			for (String s : AnswersText) {
				result += " " + s + answersCorrect.get(i);
				i++;
			}

		}
		
		
		result += " End.";

		return result;
	}

}
