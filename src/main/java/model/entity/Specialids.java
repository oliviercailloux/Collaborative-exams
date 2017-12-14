package model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import model.entity.question.Question;

@Entity
public class Specialids {

	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name") 
    private String name;
    
    @ManyToOne(optional=false,cascade=CascadeType.ALL,targetEntity=People.class)
    @Column(name = "person")
    People person;
    
    @OneToOne(optional=false,cascade=CascadeType.ALL,targetEntity=People.class)
    @Column(name = "question")
    Questions question;
 
    // default constructor
    public Specialids() {
        
    }

    //constructor
    public Specialids(String name, Question question) {
        this.name = name;
    }

    //Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

	public void setName(String name) {
		this.name = name;
	}
	
	public Questions getQuestion() {
        return question;
    }

	public void setQuestions(Questions question) {
		this.question = question;
	}
	
	public People getPeople() {
        return person;
    }

	public void setPeople(People person) {
		this.person = person;
	}
}
