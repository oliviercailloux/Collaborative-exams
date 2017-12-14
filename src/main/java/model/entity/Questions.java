package model.entity;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import javax.persistence.*;


@Entity
public class Questions {
	
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    @Column(name = "id")
	    private Integer id;

	    @Column(name = "phrasing") 
	    private String phrasing;
	    
	    @Column(name = "language")
	    private Locale language ;
	    
	    @OneToMany(cascade=CascadeType.ALL,targetEntity=People.class)
	    @Column(name = "answers")
	    private List<Answers> answers = new ArrayList<Answers>();
	    
	    @ManyToOne(optional=false,cascade=CascadeType.ALL,targetEntity=People.class)
	    @Column(name = "author")
	    People person;


	    // default constructor
	    public Questions() {
	        
	    }

	    //constructor
	    public Questions(String phrasing, Locale language, People person) {
	        this.phrasing = phrasing;
	        this.person = person;
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
		
		public Locale getLanguage() {
	        return language;
	    }

		public void setLanguage(Locale language) {
			this.language = language;
		}
		
		public People getPeople() {
	        return person;
	    }

		public void setPeople(People person) {
			this.person = person;
		}

	

}
