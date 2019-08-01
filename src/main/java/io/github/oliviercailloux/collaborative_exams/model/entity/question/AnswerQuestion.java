package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
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
@JsonbPropertyOrder({"id", "author", "exam", "question", "answer"})
@Entity
@XmlRootElement(name = "answer_question")
public class AnswerQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private Integer id;

    @Column
    @XmlElement
    private String text;

    @OneToOne
    private Person author;

    @OneToOne
    private Exam exam;

    @OneToOne
    private IQuestion question;

    @OneToOne
    private Answer answer;

    public AnswerQuestion() {
    }

    public AnswerQuestion(String text, Person author, Exam exam, IQuestion question, Answer answer) {
        this.text = text;
        this.author = author;
        this.exam = exam;
        this.question = question;
        this.answer = answer;
    }

    public AnswerQuestion(Person author, Exam exam, IQuestion question, Answer answer) {
        this.author = author;
        this.exam = exam;
        this.question = question;
        this.answer = answer;
    }

    public AnswerQuestion(Person author, Exam exam, IQuestion question, String text) {
        this.author = author;
        this.exam = exam;
        this.question = question;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public IQuestion getQuestion() {
        return question;
    }

    public void setQuestion(IQuestion question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

}
