package io.github.oliviercailloux.collaborative_exams.helper;


import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Answer;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.*;




public class XmlParser {

 static private String stringPath = "QuestionXml";


    public static File QuestionToXml(Question question) throws Exception {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Question.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(question, new File(stringPath));
            jaxbMarshaller.marshal(question, System.out);


            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Question question2  = (Question) jaxbUnmarshaller.unmarshal(new FileReader(
                    stringPath));


            System.out.print(QuestionText.QuestionToJson(question));

            return null;
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null ;
    }


    public static Question XmlToQuestion(File file) throws Exception {

        //Question question = JAXB.unmarshal(new StringReader(xml), Question.class);
        JAXBContext jaxbContext = JAXBContext.newInstance(Question.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Question question  = (Question) jaxbUnmarshaller.unmarshal(new FileReader(
                stringPath));


        System.out.print(QuestionText.QuestionToJson(question));

        return question;

    }



    public static void main(String[] args) throws Exception {
        data.constructData();

        Question question = data.getQuestions().get(0);
        File file = QuestionToXml(question);

        Question questionTransformed = XmlToQuestion(file);

        System.out.print(questionTransformed.getLanguage());

    }

}
