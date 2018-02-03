package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	private boolean correct;
	
    private String text;
    
    
    public Answer()
    {
    	
    }
    
    public Answer(String text, boolean correct)
    {
    	this.correct = correct;
    	this.text = text;
    }
    
    
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
