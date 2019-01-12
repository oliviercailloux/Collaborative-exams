package io.github.oliviercailloux.collaborative_exams.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

import com.sun.istack.logging.Logger;
import com.sun.mail.util.MailLogger;

import javax.xml.validation.Schema;

import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

/**
 * @author Mohamed 
 */
public class QuestionXML {
	//private final static Logger LOGGER = Logger.getLogger(MailLogger.class.getName(),null);

	public QuestionXML(){
		super();
	}

	public static void QuestionToXML(Question question, String FILEPATH) throws NullPointerException, IllegalArgumentException, Exception {
		try {
			SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			System.out.println("ok");

			/**
			 *  creation d'un JAXB  pour la class Question
			 */
			JAXBContext context = JAXBContext.newInstance(Question.class);

			/**
			 * Creation du marshaller context
			 */
			Marshaller marshaller = context.createMarshaller();

			/**
			 *  le Marshaller utilisera le codage UTF-8 lors de la génération de données XML 
			 */

			marshaller.setProperty("jaxb.encoding", "UTF-8") ;

			/**
			 * setting the property to show xml format output
			 * indique au Marshaller de générer le code XML avec l’indentation appropriée
			 * 
			 */
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//marshaller.setSchema(schema);

			/**
			 * write the XML file on the fileName
			 */
			marshaller.marshal(question, new File(FILEPATH));
		} catch (JAXBException ex) {
			ex.printStackTrace();
			//LOGGER.warning("Question non sérialiser");
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			//LOGGER.warning("Question non sérialiser");
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			//LOGGER.warning("Question non sérialiser");
		}
	}


    /**
     * 
     * @param question
     * @param FILEPATH
     *
     * supprimer XML avec JAXB en un objet Java,
     * @return 
     * 
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Question unmarshallerXml(Question question,String FILEPATH) throws JAXBException, FileNotFoundException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Question.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Question questiontUnmarshalled = (Question) jaxbUnmarshaller.unmarshal(new FileReader(FILEPATH));
		return questiontUnmarshalled;

	}

	
	

}
