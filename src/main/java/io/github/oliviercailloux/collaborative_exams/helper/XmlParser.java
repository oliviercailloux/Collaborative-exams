package io.github.oliviercailloux.collaborative_exams.helper;

import io.github.oliviercailloux.collaborative_exams.model.entity.Improvement;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.SameAbility;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

/**
 * @author Sid
 */
public class XmlParser {

    static private String FILEPATH = "QuestionXml";

    public static <T> void ToXml(Class<T> className, T objectT) throws JAXBException, FileNotFoundException {

        JAXBContext jaxbContext = JAXBContext.newInstance(className);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(objectT, new File(FILEPATH));
        /*
         * to use String :
         *
         * String result = "";
         * Writer writer = new StringWriter(); jaxbMarshaller.marshal(objectT, writer);
         * result = writer.toString();
         */
        jaxbMarshaller.marshal(objectT, System.out);
    }

    @SuppressWarnings({"resource", "unchecked"})
    public static <T> T FromXml(Class<T> className) throws JAXBException, FileNotFoundException {

        JAXBContext jaxbContext = JAXBContext.newInstance(className);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        T objectUnmarshalled = (T) jaxbUnmarshaller.unmarshal(new FileReader(FILEPATH));

        return objectUnmarshalled;
    }
}
