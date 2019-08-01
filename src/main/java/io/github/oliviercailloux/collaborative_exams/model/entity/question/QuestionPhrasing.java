package io.github.oliviercailloux.collaborative_exams.model.entity.question;

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
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * It's an immutable class
 *
 * @author Khaled
 */
@JsonbPropertyOrder({"id", "phrasing"})
@Entity
@XmlRootElement(name = "questionPhrasing")
public class QuestionPhrasing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;

    @Column(nullable = false)
    @XmlElement
    private String phrasing;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IQuestion> listQuestions = new ArrayList<>();

    public QuestionPhrasing() {
        this.listQuestions = new ArrayList<>();

    }

    public QuestionPhrasing(String topic) {
        this.listQuestions = new ArrayList<>();
        this.phrasing = topic;
    }

    public int getId() {
        return id;
    }

    public String getPhrasing() {
        return phrasing;
    }

    public List<IQuestion> getListeQuestions() {
        return listQuestions;
    }
}
