package GsonHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Entity.Answer;
import Entity.Author;
import Entity.Question;

public class GsonHandlerMain {

	public static void main(String[] args) {
		// Parametrages
		final GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.registerTypeAdapter(Author.class, new AuthorSerialization());
		gsonBuilder.registerTypeAdapter(Answer.class, new AnswerSerialization());
		gsonBuilder.registerTypeAdapter(Question.class, new QuestionSerialization());

		gsonBuilder.setPrettyPrinting();

		final Gson gson = gsonBuilder.create();

		// Author:
		final Author myAuthor = new Author();
		myAuthor.setIdAuthor(1);
		myAuthor.setNameAuthor("Badga 3ebdelmajid lampoule");
		myAuthor.setEmail("Badga@gmail.com");

		final Answer myAnswer1 = new Answer();
		myAnswer1.setIdAnswer(1);
		myAnswer1.setText("Oui");
		myAnswer1.setCorrect(true);

		// Answer 2:
		final Answer myAnswer2 = new Answer();
		myAnswer2.setIdAnswer(2);
		myAnswer2.setText("Non");
		myAnswer2.setCorrect(false);

		// Question:
		final Question myQuestion = new Question();
		myQuestion.setIdQuestion(2);
		myQuestion.setAuthor(myAuthor);
		myQuestion.addAnswer(myAnswer1);
		myQuestion.addAnswer(myAnswer2);
		myQuestion.setLanguage("Français");
		myQuestion.setPhrasing("Est ce que .........?");

		// Answer Serializer
		final String jsonAnswer = gson.toJson(myAnswer1);
		System.out.println(jsonAnswer);

		// Author Serializer
		final String jsonAuthor = gson.toJson(myAuthor);
		System.out.println(jsonAuthor);

		// Question Serializer
		final String jsonQuestion = gson.toJson(myQuestion);
		System.out.println(jsonQuestion);

		// Answer Deserializer
		final Answer answerDeserialized = gson.fromJson(jsonAnswer, Answer.class);
		System.out.println(answerDeserialized);

		// Author Deserializer
		final Author authorDeserialized = gson.fromJson(jsonAuthor, Author.class);
		System.out.println(authorDeserialized);

		// Question Deserializer
		final Question questionDeserialized = gson.fromJson(jsonQuestion, Question.class);
		System.out.println(questionDeserialized);

		Answer testAnswer = questionDeserialized.getAnswers().get(0);
		System.out.println(testAnswer + " Test final___");

	}

}
