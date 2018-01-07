package model.entity.question;

import javax.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"text","correct"})
public class Answer {
	
	private  boolean correct;
    private String text = null;
    
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
