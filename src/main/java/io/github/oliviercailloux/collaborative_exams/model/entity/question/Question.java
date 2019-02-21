package io.github.oliviercailloux.collaborative_exams.model.entity.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.CascadeType;
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

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import java.io.Serializable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This Class represents Question
 * <p>
 * Question is immuable
 *
 * @author badga & Sid
 */
@JsonbPropertyOrder({"id", "author", "questionPhrasing", "language", "type", "isCorrect", "answers"})
@XmlRootElement
@Entity
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")})
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;

    /**
     * represent the questionPhrasing of the question
     * <p>
     * Not <code>null</code>.
     */
    @XmlElement
    @Column(nullable = false)
    private final QuestionPhrasing questionPhrasing;

    /**
     * represent the language of Question
     * <p>
     * <p>
     * Not <code>null</code>, may be empty.
     */
    @XmlElement
    @Column(nullable = false)
    private String language;

    @XmlElement
    @Column(nullable = false)
    private DifficultyType difficultyType;
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

    @Column(nullable = false)
    @XmlElement
    private int countSelection = 0;

    @Column(nullable = false)
    @XmlElement
    private int countCorrectAnswer = 0;

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

    @ManyToMany(mappedBy = "listQuestions")
    private List<Exam> listExams = new ArrayList<>();

    /**
     * Returns a new Question. Not <code>null</code>.
     */
    public Question() {

        questionPhrasing = null;
    }

    public Question(QuestionPhrasing questionPhrasing) {
        this.questionPhrasing = questionPhrasing;
    }

    /**
     * return new Question T/F or Y/N
     *
     * @param questionPhrasing questionPhrasing of question
     * @param language Language of question
     * @param author Person how represente the author of question
     * @param type Type of question
     */
    public Question(QuestionPhrasing questionPhrasing, String language, Person author, QuestionType type, boolean isCorrect) {
        this.questionPhrasing = Objects.requireNonNull(questionPhrasing);
        this.language = Objects.requireNonNull(language);
        this.author = Objects.requireNonNull(author);
        this.type = Objects.requireNonNull(type);
        this.isCorrect = Objects.requireNonNull(isCorrect);
    }

    /**
     * return Question Free
     *
     * @param questionPhrasing questionPhrasing of question
     * @param language Language of question
     * @param author Person how represente the author of question
     * @param type Type of question
     * @Param answer the answer of the free question
     */
    public Question(QuestionPhrasing questionPhrasing, String language, Person author, QuestionType type, Answer answer)
            throws Exception {
        this.questionPhrasing = Objects.requireNonNull(questionPhrasing);
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
     * @param questionPhrasing questionPhrasing of question
     * @param language Language of question
     * @param author Person how represente the author of question
     * @param type Type of question
     * @param answers represente the list of answer of the question
     * @throws java.lang.Exception
     */
    public Question(QuestionPhrasing questionPhrasing, String language, Person author, QuestionType type, List<Answer> answers)
            throws Exception {
        this.questionPhrasing = Objects.requireNonNull(questionPhrasing);
        this.language = Objects.requireNonNull(language);
        this.author = Objects.requireNonNull(author);
        this.type = Objects.requireNonNull(type);

        for (Answer answer : answers) {
            answer.setQuestionIfNull(this);
        }

        this.answers = Objects.requireNonNull(answers);
    }

    public Question(QuestionPhrasing questionPhrasing, int countCorrectAnswer, int countSelection) {
        this.questionPhrasing = questionPhrasing;
        this.countCorrectAnswer = countCorrectAnswer;
        this.countSelection = countSelection;
    }

    /**
     * Returns this Question’s id.
     *
     * @return not <code>null</code>.
     */
    public int getId() {
        return id;
    }

    public int getCountSelection() {
        return countSelection;
    }

    public void setCountSelection(int countSelection) {
        this.countSelection = countSelection;
    }

    public int getCountCorrectAnswer() {
        return countCorrectAnswer;
    }

    public void setCountCorrectAnswer(int countCorrectAnswer) {
        this.countCorrectAnswer = countCorrectAnswer;
    }

    public DifficultyType getDifficultyType() {
        return difficultyType;
    }

    public void setDifficultyType(DifficultyType difficultyType) {
        this.difficultyType = difficultyType;
    }

    /**
     * Returns this Question’s language.
     *
     * @return String not null.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Returns this Question’s author.
     *
     * @return Person not null and immuable.
     */
    public Person getAuthor() {
        return author;
    }

    /**
     * Returns this Question’s type.
     *
     * @return String not null.
     */
    public QuestionType getType() {

        return type;
    }

    /**
     * Returns this Question’s questionPhrasing.
     *
     * @return QuestionPhrasing not <code>null</code>.
     */
    public QuestionPhrasing getPhrasing() {

        return this.questionPhrasing;
    }

    /**
     * Returns this Question’s answers.
     *
     * @return String can be null, if the Question is TF/ YN / QCM
     */
    public List<Answer> getAnswers() {
        if (this.answers == null) {
            return answers = new ArrayList<>();
        }
        return answers;
    }

    /**
     * @Return boolean
     */
    public boolean getCorrect() {

        return isCorrect;
    }

    public boolean equals(Question question) {

        if (question == this) {
            return true;
        }
        if (question != null && (question.getClass().equals(this.getClass()))) {

            return (this.questionPhrasing.equals(question.getPhrasing()) && this.author.equals(question.getAuthor())
                    && (this.id == question.getId()) && (this.getCorrect() == question.getCorrect())
                    && (this.getLanguage().equals(question.getLanguage())));

        }
        return false;
    }

    public List<Exam> getListExams() {
        return listExams;
    }

    public void setListExams(List<Exam> listExams) {
        this.listExams = listExams;
    }

}
