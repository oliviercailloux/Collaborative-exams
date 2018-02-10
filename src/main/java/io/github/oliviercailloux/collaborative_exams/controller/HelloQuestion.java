package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.AnswerService;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@Path("hello")
public class HelloQuestion {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private PersonService personService;

	@Inject
	private AnswerService answerService;

	@Inject
	private QuestionService questionService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() throws Exception {
		String result = "Debut : \n";
		/*
		 * Person person = new Person("toto@test.com"); personService.persist(person);
		 * 
		 * Answer answer = new Answer("Test answerService add !! ", true);
		 * answerService.persist(answer);
		 * 
		 * List<Person> persons =personService.getAll();
		 * 
		 * result += "Person : { "; for (Person p : persons) { result += p.getId() + " "
		 * + p.getEmail() + " "; } result += " } \n Answers : {";
		 * 
		 * 
		 * List<Answer> answers = answerService.getAll(); for (Answer a : answers) {
		 * result += a.getId() + " " + a.getText() + " " + a.isCorrect() + " " +
		 * a.getQuestion(); } result += " }   Questions : { ";
		 */
		data.constructData();

		Question q = data.getQuestions().get(0);
		result += QuestionText.QuestionToJson(q);

		List<Answer> answers = q.getAnswers();
		for (Answer answer : answers) {
			answer.setQuestion(q);
			//answerService.persist(answer);
		}
		
		Person author = q.getAuthor();
		author.addQuestion(q);
		//personService.persist(author);
			
		questionService.persist(q);

		/*
		 * List<Question> questions = questionService.getAll(); for (Question question :
		 * questions) { result += QuestionText.QuestionToJson(question); }
		 * 
		 * result += "} \n";
		 * 
		 */
		return "Hello World \n " + result;
	}
	
	public static void main(String[] argv) throws Exception {
		QuestionService questionService = new QuestionService();
		
		data.constructData();
		String result = "";
		Question q = data.getQuestions().get(0);
		//result += QuestionText.QuestionToJson(q);

		List<Answer> answers = q.getAnswers();
		for (Answer answer : answers) {
			answer.setQuestion(q);
			result += answer;
			//answerService.persist(answer);
		}
		
		Person author = q.getAuthor();
		result += author;
		author.addQuestion(q);
		//personService.persist(author);
			
		//questionService.persist(q);
		
		System.out.println(result);
	}
}