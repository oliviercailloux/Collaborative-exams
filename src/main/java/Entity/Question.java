package Entity;


import java.util.ArrayList;
import java.util.List;

 
public class Question implements MultipleChoiceQuestion {

	private int idQuestion;
	private String phrasing;
	private String language;
	private Author author;
	private List<Answer> answer;

	
	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}


	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer =  answer;
	}
	
	public Question addAnswer(Answer answer)
	{
		this.answer.add(answer);
		
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPhrasing() {
		return phrasing;
	}

	public void setPhrasing(String phrasing) {
		this.phrasing = phrasing;
	}

}
