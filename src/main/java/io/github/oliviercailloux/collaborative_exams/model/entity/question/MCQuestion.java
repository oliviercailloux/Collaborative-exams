package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import io.github.oliviercailloux.collaborative_exams.model.entity.Language;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import java.io.Serializable;

/**
 * This Class represents Question
 * <p>
 * Question is immutable
 *
 * @author Amine BOUKALA
 */
@JsonbPropertyOrder({"id", "author", "phrasing", "language", "type", "answers"})

@XmlRootElement
@Entity
public class MCQuestion implements IQuestion, Serializable {

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
    private String phrasing;

    /**
     * represent the language of Question
     * <p>
     * <p>
     * Not <code>null</code>, may be empty.
     */
    @XmlElement
    @Column(nullable = false)
    private Language language;

    /**
     * respresente the Author
     *
     * @see Person Not <code>null</code>.
     */
    @ManyToOne
    @XmlElement(name = "author")
    private Person author;

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
    //@OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    @XmlElementWrapper(name = "answers")
    @XmlElement(name = "answer")
    protected List<Answer> answers;

    /**
     * Returns a new Question. Not <code>null</code>.
     */
    /**
     * return new MCQ Question
     *
     * @param phrasing phrasing of question
     * @param language Language of question
     * @param author Person how represente the author of question
     * @param type Type of question
     */
    public MCQuestion(String phrasing, Language language, Person author, QuestionType type) {
        this.phrasing = Objects.requireNonNull(phrasing);
        this.language = Objects.requireNonNull(language);
        this.author = Objects.requireNonNull(author);
        this.type = Objects.requireNonNull(type);

    }

    public MCQuestion(String phrasing, List<Answer> answers) {
        this.phrasing = phrasing;
        this.answers = answers;
    }

    public MCQuestion() {
    }

    /**
     * return new MCQ Question
     *
     * @param phrasing phrasing of question
     * @param language Language of question
     * @param author Person how represente the author of question
     * @param type Type of question
     * @param answers represente the list of answer of the question
     * @throws java.lang.Exception
     */
    public MCQuestion(String phrasing, Language language, Person author, QuestionType type, List<Answer> answers) throws Exception {
        this.phrasing = Objects.requireNonNull(phrasing);
        this.language = Objects.requireNonNull(language);
        this.author = Objects.requireNonNull(author);
        this.type = Objects.requireNonNull(type);
        for (Answer answer : answers) {
            answer.setQuestionIfNull(this);
        }
        this.answers = Objects.requireNonNull(answers);
    }

    /**
     * Returns this Question’s id.
     *
     * @return not <code>null</code>.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Returns this Question’s language.
     *
     * @return String not null.
     */
    @Override
    public Language getLanguage() {
        return language;
    }

    /**
     * Returns this Question’s author.
     *
     * @return Person not null and immuable.
     */
    @Override
    public Person getAuthor() {
        return author;
    }

    /**
     * Returns this Question’s type.
     *
     * @return String not null.
     */
    @Override
    public QuestionType getType() {

        return type;
    }

    /**
     * Returns this Question’s phrasing.
     *
     * @return String not <code>null</code>.
     */
    @Override
    public String getPhrasing() {
        return this.phrasing;
    }

    @Override
    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(this.answers);
    }

    public List<Answer> getListAnswers() {
        return answers;
    }
}
