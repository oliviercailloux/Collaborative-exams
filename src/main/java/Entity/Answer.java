package Entity;

public class Answer {

	private int Answer;
	private String text;
	private boolean correct;
	
	
	public int getAnswer() {
		return Answer;
	}

	public void setAnswer(int answer) {
		Answer = answer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

}
