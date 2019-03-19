package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Answer is immuable, you can add the question the first time
 */
@JsonbPropertyOrder({"id", "text", "correct"})
@Entity
@XmlRootElement(name = "answer")
@NamedQueries({
    @NamedQuery(name = "Answer.findByIdQuestion", query = "SELECT a FROM Answer a WHERE a.question.id = :idQuestion ")})
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;

    @Column(nullable = false)
    @XmlElement
    private boolean correct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stats_id", referencedColumnName = "id")
    private Stats stats;

    @Column(nullable = false)
    @XmlElement
    private String text;

    @XmlElement
    @Column(nullable = false)
    private DifficultyType difficultyType;

    @ManyToOne
    private Question question;

    public Answer() {

    }

    public Answer(String text, boolean correct) {
        this.correct = correct;
        this.text = text;
    }

    public Answer(String text, boolean correct, Stats stats) {
        this.correct = correct;
        this.text = text;
        this.stats = stats;
    }

    public DifficultyType getDifficultyType() {
        return difficultyType;
    }

    public void setDifficultyType(DifficultyType difficultyType) {
        this.difficultyType = difficultyType;
    }

    public Stats getStats() {
        if (stats == null) {
            stats = new Stats(0, 0);
        }
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public boolean isCorrect() {
        return correct;
    }

    public String getText() {
        return text;
    }

    /**
     * add question to answers if question is null , else throw new exception,
     * for add
     *
     * @param question the question how have this answer
     * @throws java.lang.Exception
     */
    public void setQuestionIfNull(Question question) throws Exception {
        if (this.question == null) {
            this.question = question;
        } else {
            throw new Exception("the Answer is already linked to a Question and it's immuable");
        }
    }

    public int getId() {
        return id;
    }

    @JsonbTransient
    public Question getQuestion() {
        return this.question;
    }

}
