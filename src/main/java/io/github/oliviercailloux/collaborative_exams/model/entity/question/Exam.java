package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * It's an immutable class
 *
 * @author Khaled
 */
@JsonbPropertyOrder({"id", "nom"})
@Entity
@XmlRootElement(name = "exam")
public class Exam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;

    @Column(nullable = false)
    @XmlElement
    private String nom;

    @ManyToOne
    @XmlElement(name = "author")
    private Person author;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "exam_question",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Question> listQuestions;

    public Exam() {

    }

    public Exam(String nom) {
        this.nom = nom;
        this.listQuestions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Person getAuthor() {
        return author;
    }

    public List<Question> getListeQuestions() {
        return listQuestions;
    }
}
