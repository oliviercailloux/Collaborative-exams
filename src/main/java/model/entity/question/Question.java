package model.entity.question;

import java.util.ArrayList;
import java.util.Locale;

import javax.json.bind.annotation.JsonbPropertyOrder;

import model.entity.Person;




@JsonbPropertyOrder({"id","phrasing","language","author","answers","type"})
public class Question {
	
	private int id ;
    private String phrasing ;
	private Locale language ;
	private model.entity.Person author ;
	private java.util.List<Answer> answers;
	private QuestionType type;
//	public static int questionCount;
	
	
	public Question()
	{
		
	}
	
	public Question(int id, String phrasing){
		
		this.id = id ;
		this.phrasing = phrasing;
		this.author = new Person(1,"jeha@tmenyik.dz");
		ArrayList<Answer> a = new ArrayList<Answer>();
		a.add(new Answer(true,"première reponse"));
		this.answers = a;
		this.type = QuestionType.Free;
		
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhrasing() {
		return phrasing;
	}
	public void setPhrasing(String phrasing) {
		this.phrasing = phrasing;
	}
	public Locale getLanguage() {
		return language;
	}
	public void setLanguage(Locale language) {
		this.language = language;
	}
	public model.entity.Person getAuthor() {
		return author;
	}
	public void setAuthor(model.entity.Person author) {
		this.author = author;
	}
	public java.util.List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(java.util.ArrayList<Answer> answers) {
		this.answers = answers;
	}
	public QuestionType getType() {
		return type;
	}
	
	
	public void setType(QuestionType type) {
		this.type = type;
	}
	
	
	public String toString()
	{
		return this.getPhrasing();
		
	}
	
	
	
}
