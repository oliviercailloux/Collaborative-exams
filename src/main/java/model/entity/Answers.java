package model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answers {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "correct") 
    private boolean correct;
    
    @Column(name = "phrasing") 
    private String phrasing;
    
    @ManyToOne(optional=false,cascade=CascadeType.ALL,targetEntity=People.class)
    @Column(name = "questionid")
    Questions question;
    
 
    // default constructor
    public Answers() {
        
    }

    //constructor
    public Answers(String phrasing, boolean correct, Questions question) {
        this.phrasing = phrasing;
    }

    //Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public String getPhrasing() {
        return phrasing;
    }

	public void setPhrasing(String phrasing) {
		this.phrasing = phrasing;
	}
	
	public boolean getCorrect() {
        return correct;
    }

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	public Questions getQuestion() {
        return question;
    }

	public void setQuestions(Questions question) {
		this.question = question;
	}
	
	
}
