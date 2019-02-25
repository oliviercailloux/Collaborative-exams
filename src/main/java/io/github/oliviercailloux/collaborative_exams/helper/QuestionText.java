package io.github.oliviercailloux.collaborative_exams.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;


import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.logging.Logger;
import com.sun.mail.util.MailLogger;

import javax.xml.namespace.QName ;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import javax.xml.bind.Marshaller;
/**
 * 
 * @author Mohamed
 *
 */
@ApplicationScoped
public class QuestionText {
	private static ObjectMapper mapper = new ObjectMapper();

	private static final Logger LOGGER = Logger.getLogger(MailLogger.class);

	public static Question JsonToQuestion(String json) throws Exception {

		try {
			return mapper.readValue(json, Question.class);
		} catch (IOException e) {
			LOGGER.severe("Error to cast  JSON to Question" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	public static String QuestionToJson(Question question) throws Exception {

		try {
			return mapper.writeValueAsString(question);
		} catch (JsonProcessingException e) {
			LOGGER.severe("Error to cast QUESTION to OBJECT" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
     /** Methode to convert JSON to Object
      * 
      * @param className
      * @param json
      * @return
      * @throws Exception
      */
	public static <T> T JsonToObject(Class<T> className, String json) throws Exception {
		try {
			return mapper.readValue(json, className);
		} catch (IOException e) {
			LOGGER.severe("Error to cast " + className + " JSON to OBJECT" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Methode to convert Object to JSON
	 * @param className
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static <T> String ObjectToJson(Class<T> className, T object) throws Exception {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			LOGGER.severe("Error to cast " + className + " JSON to OBJECT" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	public static String QuestionsToJson(List<Question> question) throws Exception {

		try (Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true))) {
			return jsonb.toJson(question);
		}
	}
	/**
	 * convert Json To array Object
	 * @param json
	 * @param className
	 * @return Array
	 * @throws Exception
	 */
	
	public <T> List <T> JsonToObjectList(String json,Class <T []> className) throws Exception{
		T[] objects = mapper.readValue(json, className);
		return Arrays.asList(objects);
	}
	/**
	 * Convert ObjectList to Json
	 * @param className
	 * @return
	 * @throws Exception
	 */
	
	public <T> List <String> ObjectListToJson(Class <T []> className) throws Exception{
		try{
		String objects = mapper.writeValueAsString(className);
		return Arrays.asList(objects);
		} catch(JsonProcessingException e) {
			LOGGER.severe("Error to cast " + className + " ObjectList to Json" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 *  Convert Object To Xml
	 * @param object
	 * @param name
	 * @return String format XML
	 */
	public static String convertObjectToXML(Object object,String name) {
        try {
            StringWriter stringWriter = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            QName  qName = new QName(object.getClass().toString(), name);
            Object root = new JAXBElement<Object>(qName,java.lang.Object.class, object);
            marshaller.marshal(root, stringWriter);
            String result = stringWriter.toString();
            return result;
      }catch (Exception e) {
    	  LOGGER.severe("Error to convert ObjectList to XML" + e.getMessage());
          e.printStackTrace();
      }
      return null;
  }
}
