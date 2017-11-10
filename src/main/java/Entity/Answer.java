package Entity;

public class Answer {

	private int idAnswer;
	private String text;
	private boolean correct;
	
	public Answer()
	{
		
	}
	
	public Answer(int idAnswer, String text, boolean correct) {
		this.idAnswer = idAnswer; 
		this.text = text;
		this.correct = correct; 
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

	public int getIdAnswer() {
		return idAnswer;
	}

	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}

}
