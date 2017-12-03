package model.entity.question;

public class Question {
	
	private int id = 0;
    private String phrasing = null;
	private String language = null;
	private model.entity.Person author = null;
	private java.util.ArrayList<Answer> answers = null;
	private QuestionType type = null;
	public static int questionCount = 0;
	
	
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public model.entity.Person getAuthor() {
		return author;
	}
	public void setAuthor(model.entity.Person author) {
		this.author = author;
	}
	public java.util.ArrayList<Answer> getAnswers() {
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
	
	
	
}
