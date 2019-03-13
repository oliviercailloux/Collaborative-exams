package io.github.oliviercailloux.collaborative_exams.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema ;
import javax.xml.validation.Validator;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

/**
 * @author Mohamed 
 */
public class QuestionXml {
	

	public QuestionXml(){
		super();
	}

	public static void QuestionToXML(Question question, String FILEPATH) throws NullPointerException, IllegalArgumentException, Exception {

			SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = sf.newSchema(new File("NewShemas.xsd"));
			System.out.println("ok");

			JAXBContext context = JAXBContext.newInstance(Question.class);
			
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty("jaxb.encoding", "UTF-8") ;
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setSchema(schema);
			
			marshaller.marshal(question, new File(FILEPATH));
	}


    /**
     * 
     * @param question
     * @param FILEPATH
     *
     * Delet XML with JAXB in a objet Java,
     * @return Object Question 
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