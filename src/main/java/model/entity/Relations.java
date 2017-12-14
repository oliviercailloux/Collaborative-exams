package model.entity;
import javax.persistence.*;

@Entity
public class Relations {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name") 
    private String name;
    
    @ManyToOne(optional=false,cascade=CascadeType.ALL,targetEntity=People.class)
    @Column(name = "question1")
    Questions question1;
    
    @ManyToOne(optional=false,cascade=CascadeType.ALL,targetEntity=People.class)
    @Column(name = "question2")
    Questions question2;
    
    @ManyToOne(optional=false,cascade=CascadeType.ALL,targetEntity=People.class)
    @Column(name = "author")
    People person;

    // default constructor
    public Relations() {
        
    }

    //constructor
    public Relations(String name, Questions question1, Questions question2, People person) {
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
	
	public Questions getQuestion1() {
        return question1;
    }

	public void setQuestion1(Questions question1) {
		this.question1 = question1;
	}
	
	public Questions getQuestion2() {
        return question2;
    }

	public void setQuestion2(Questions question2) {
		this.question2 = question2;
	}
	
	public People getPeople() {
        return person;
    }

	public void setPeople(People person) {
		this.person = person;
	}


}
