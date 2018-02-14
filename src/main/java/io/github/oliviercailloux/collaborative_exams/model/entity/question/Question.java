package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

/**
 * This Class represents Question
 * <p>
 * Question is immuable
 *
 * @author badga & Sid
 */
@JsonbPropertyOrder({"id", "author", "phrasing", "language", "type", "isCorrect", "answers"})

@XmlRootElement
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;

    /**
     * represent the phrasing of the question
     * <p>
     * Not <code>null</code>.
     */
    @XmlElement
    @Column(nullable = false)
    private final String phrasing;

    /**
     * represent the language of Question
     * <p>
     * <p>
     * Not <code>null</code>, may be empty.
     */
    @XmlElement
    @Column(nullable = false)
    private String language;


    /**
     * respresente the Author
     *
     * @see Person Not <code>null</code>.
     */

    @ManyToOne
    @XmlElement(name = "author")
    private Person author;

    /**
     * respresente if the response whas correct in T/F or Y/N question
     * <p>
     * Null if type of question is Free or QCM
     */
    @Column(nullable = true)
    private boolean isCorrect;

    /**
     * Respresent the type of question
     *
     * @see QuestionType Not <code>null</code>.
     */
    @XmlElement
    @Column(nullable = false)
    private QuestionType type;


    /**
     * Represent the possible answers of a MQC Question
     */

    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    @XmlElementWrapper(name = "answers")
    @XmlElement(name = "answer")
    private List<Answer> answers;


    /**
     * Returns a new Question. Not <code>null</code>.
     */
    public Question() {

        phrasing = null;
    }

    /**
     * return new Question T/F or Y/N
     *
     * @param phrasing phrasing of question
     * @param language Language of question
     * @param author   Person how represente the author of question
     * @param type     Type of question
     */
    public Question(String phrasing, String language, Person author, QuestionType type, boolean isCorrect) {
        this.phrasing = Objects.requireNonNull(phrasing);
        this.language = Objects.requireNonNull(language);
        this.author = Objects.requireNonNull(author);
        this.type = Objects.requireNonNull(type);
        this.isCorrect = Objects.requireNonNull(isCorrect);
    }

    /**
     * return Question Free
     *
     * @param phrasing phrasing of question
     * @param language Language of question
     * @param author   Person how represente the author of question
     * @param type     Type of question
     * @Param answer   the answer of the free question
     */
    public Question(String phrasing, String language, Person author, QuestionType type, Answer answer) throws Exception {
        this.phrasing = Objects.requireNonNull(phrasing);
        this.language = Objects.requireNonNull(language);
        this.author = Objects.requireNonNull(author);
        this.type = Objects.requireNonNull(type);
        this.answers = new ArrayList<>();
        answer.setQuestionIfNull(this);
        this.answers.add(answer);
    }


    /**
     * return new MCQ Question
     *
     * @param phrasing phrasing of question
     * @param language Language of question
     * @param author   Person how represente the author of question
     * @param type     Type of question
     * @param answers  represente the list of answer of the question
     */
    public Question(String phrasing, String language, Person author, QuestionType type, List<Answer> answers) throws Exception {
        this.phrasing = Objects.requireNonNull(phrasing);
        this.language = Objects.requireNonNull(language);
        this.author = Objects.requireNonNull(author);
        this.type = Objects.requireNonNull(type);

        for (Answer answer : answers)
            answer.setQuestionIfNull(this);

        this.answers = Objects.requireNonNull(answers);
    }


    /**
     * Returns this Question’s id.
     *
     * @return not <code>null</code>.
     */

    public int getId() {
        return id;
    }

    /**
     * Returns this Question’s language.
     *
     * @return String not <code>null</code>.
     */

    public String getLanguage() {
        return language;
    }

    /**
     * Returns this Question’s author.
     *
     * @return Person not <code>null</code> and immuable.
     */

    public Person getAuthor() {
        return author;
    }

    /**
     * Returns this Question’s type.
     *
     * @return String not <code>null</code>.
     */

    public QuestionType getType() {

        return type;
    }

    /**
     * Returns this Question’s phrasing.
     *
     * @return String not <code>null</code>.
     */

    public String getPhrasing() {

        return this.phrasing;
    }

    /**
     * Returns this Question’s answers.
     *
     * @return String can be <code>null</code>. if the Question is TF/ YN / QCM
     */
    public List<Answer> getAnswers() {

        if(this.answers==null) return null;

        return Collections.unmodifiableList(this.answers);
    }

    /**
     * @Return boolean
     */
    public boolean getCorrect() {

        return isCorrect;
    }

    public void setId(int id) {
        this.id = id;
    }



    public boolean equals(Question question) {

        if (question == this) return true;
        if (question != null && (question.getClass().equals(this.getClass()))) {

            return (this.phrasing.equals(question.getPhrasing()) && this.author.equals(question.getAuthor())
                    && (this.id == question.getId()) && (this.getCorrect() == question.getCorrect())
                    && (this.getLanguage().equals(question.getLanguage())));

        }
        return false;
    }


}
