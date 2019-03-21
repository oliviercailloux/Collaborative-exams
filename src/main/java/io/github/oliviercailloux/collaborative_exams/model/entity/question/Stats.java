/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbPropertyOrder;
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
@JsonbPropertyOrder({"id", "topic"})
@Entity
@XmlRootElement(name = "exam")
public class Stats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;

    //compteur qui s'incremente de 1 chaque choix que cette reponse participe a un QCM
    @Column(nullable = false)
    @XmlElement
    private int countParticipat;

    //compteur qui s'incremente de 1 chaque choix que cette reponse a été selectionée par un utilisateur
    @Column(nullable = false)
    @XmlElement
    private int countSelect;

    @OneToOne(mappedBy = "answer")
    private Answer answer;

    public Stats() {

    }

    public Stats(Answer answer) {
        this.answer = answer;
    }

    public Stats(int countParticipat, int countSelect) {
        this.countParticipat = countParticipat;
        this.countSelect = countSelect;
    }

    public Stats(int countParticipat, int countSelect, Answer answer) {
        this.countParticipat = countParticipat;
        this.countSelect = countSelect;
        this.answer = answer;
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
