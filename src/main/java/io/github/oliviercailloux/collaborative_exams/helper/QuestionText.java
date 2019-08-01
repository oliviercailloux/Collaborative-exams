package io.github.oliviercailloux.collaborative_exams.helper;


import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.sun.mail.util.MailLogger;
import java.util.logging.Logger;
import javax.xml.namespace.QName ;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
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
	public static Jsonb getCreate(){
        
        return JsonbBuilder.create();
    }
	
    /**
     * Convert Json To Question
     * @param json
     * @return
     * @throws Exception
     */
	public static Question JsonToQuestion(String json) throws Exception {

		  return getCreate().fromJson(json, Question.class);
		
	}
     /**
      *  Convert Question To Json 
      * @param question
      * @return Question Json
      */
	public static String QuestionToJson(Question question) throws Exception {
	
	 return  getCreate().toJson(question);
	}
	
	
	
     /** Methode to convert JSON to Object
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
	 * @param className
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static <T> String ObjectToJson(Class<T> className,T object) throws Exception {
		 return  getCreate().toJson(object);
	}
	
	public static <T> String ObjectToJson(List<T> object) throws Exception {
	       return getCreate().toJson(object);
	   
	}
    
	/**
	 * convert List of Question to Json
	 * @param question
	 * @return Json String
	 * @throws Exception
	 */
	public static String QuestionsToJson(List<Question> question) throws Exception {
          
		return getCreate().toJson(question);
	}
	/**
	 * convert Json To array Object
	 * @param json
	 * @param className
	 * @return Array
	 * @throws Exception
	 */
	
	public <T> List <T> JsonToObjectList(String json,Class <T []> className) throws Exception{
		T[] objects= getCreate().fromJson(json, className);
		return Arrays.asList(objects);
	}
	/**
	 * this method converts a list of objects into JSON
	 * @param objets
	 * @return Json
	 * @throws Exception
	 */
	
	public <T> String ObjectListToJson(List <T []> objets) throws Exception{
		String objects =  getCreate().toJson(objets);
		return  objects;
	
	}
	/**
	 *  Convert Object To Xml
	 * @param object
	 * @param name
	 * @return String format XML
	 */
	public static String convertObjectToXML(Object object,String name) throws JAXBException, FileNotFoundException {
        
            StringWriter stringWriter = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            QName  qName = new QName(object.getClass().toString(), name);
            Object root = new JAXBElement<Object>(qName,java.lang.Object.class, object);
            marshaller.marshal(root, stringWriter);
            String result = stringWriter.toString();
            return result;
   
  }

}
