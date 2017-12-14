package model.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email") 
    private String email;
    
    @OneToMany(cascade=CascadeType.ALL,targetEntity=People.class)
    @Column(name = "questionid")
    private List<Questions> questions = new ArrayList<Questions>();

    // default constructor
    public People() {
        
    }

    public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	//constructor
    public People(String email) {
        this.email = email;
    }

    //Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
        return email;
    }

	public void setEmail(String email) {
		this.email = email;
	}

}

