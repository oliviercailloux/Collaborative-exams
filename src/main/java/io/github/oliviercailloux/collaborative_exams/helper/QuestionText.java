package io.github.oliviercailloux.collaborative_exams.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import com.sun.mail.util.MailLogger;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import java.util.logging.Logger;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Mohamed
 *
 */
@ApplicationScoped
public class QuestionText {

    private static final Logger LOGGER = Logger.getLogger(MailLogger.class.getName());

    /**
     *
     * @return new Instance JsonBuilder
     */
    public static Jsonb getCreate() {

        return JsonbBuilder.create();
    }

    /**
     * Convert Json To Question
     *
     * @param json
     * @return
     * @throws Exception
     */
    public static Question JsonToQuestion(String json) throws Exception {

        return getCreate().fromJson(json, Question.class);

    }

    /**
     * Convert Question To Json
     *
     * @param question
     * @return Question Json
     */
    public static String QuestionToJson(Question question) throws Exception {

        return getCreate().toJson(question);
    }

    /**
     * Methode to convert JSON to Object
     *
     * @param className
     * @param json
     * @return Object <T>
     * @throws Exception
     */
    public static <T> T JsonToObject(Class<T> className, String json) throws Exception {

        return getCreate().fromJson(json, className);
    }

    /**
     * Methode to convert Object to JSON
     *
     * @param className
     * @param object
     * @return
     * @throws Exception
     */
    public static <T> String ObjectToJson(Class<T> className, T object) throws Exception {
        return getCreate().toJson(object);
    }

    /**
     * convert List of Question to Json
     *
     * @param question
     * @return Json String
     * @throws Exception
     */
    public static String QuestionsToJson(List<Question> question) throws Exception {

        return getCreate().toJson(question);
    }

    public static String MessageTonJson(Object o) throws Exception {
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonStr = mapperObj.writeValueAsString(o);
        return jsonStr;

    }
}
