/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Khaled
 */
@JsonbPropertyOrder({"id", "phrasing"})
@Entity
@XmlRootElement(name = "questionPhrasing")
public class QuestionPhrasing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;

    @Column(nullable = false)
    @XmlElement
    private String phrasing;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "exam_question",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Question> listQuestions = new ArrayList<>();

    public QuestionPhrasing() {
    }

    public QuestionPhrasing(String phrasing) {
        this.phrasing = phrasing;
    }

    public int getId() {
        return id;
    }

    public String getPhrasing() {
        return phrasing;
    }

    public void setPhrasing(String phrasing) {
        this.phrasing = phrasing;
    }

    public List<Question> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<Question> listQuestions) {
        this.listQuestions = listQuestions;
    }

}
