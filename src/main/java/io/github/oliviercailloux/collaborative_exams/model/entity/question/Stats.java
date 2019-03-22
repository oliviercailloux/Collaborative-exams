package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Khaled
 */
@Entity
@XmlRootElement(name = "stats")
public class Stats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;

    //count number of candidates
    @Column(nullable = false)
    @XmlElement
    private int countParticipat = 0;

    //count number of numbre of selection ot answer by candidates
    @Column(nullable = false)
    @XmlElement
    private int countSelect = 0;

    @OneToOne(mappedBy = "answer")
    private Answer answer;

    public Stats() {
    }

    public Stats(int countParticipat, int countSelect) {
        this.countParticipat = countParticipat;
        this.countSelect = countSelect;
    }

    public int getId() {
        return id;
    }

    public int getCountParticipat() {
        return countParticipat;
    }

    public void setCountParticipat(int countParticipat) {
        this.countParticipat = countParticipat;
    }

    public int getCountSelect() {
        return countSelect;
    }

    public void setCountSelect(int countSelect) {
        this.countSelect = countSelect;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

}
