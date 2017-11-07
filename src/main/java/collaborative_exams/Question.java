package collaborative_exams;


import java.util.ArrayList;
import java.util.List;

public class Question {

	private int idQuestion;
	private String Question;
	private Author author;
	private ArrayList<Answer> answer;

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
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

	public void setAnswer(ArrayList<Answer> answer) {
		this.answer =  answer;
	}

}
