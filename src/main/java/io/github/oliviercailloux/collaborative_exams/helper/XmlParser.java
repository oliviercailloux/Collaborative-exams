package io.github.oliviercailloux.collaborative_exams.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author sidou 
 * 
 * 
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
		 * String result = ""; Writer writer = new StringWriter();
		 * jaxbMarshaller.marshal(objectT, writer); result = writer.toString();
		 */
		jaxbMarshaller.marshal(objectT, System.out);
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public static <T> T FromXml(Class<T> className) throws JAXBException, FileNotFoundException {

		JAXBContext jaxbContext = JAXBContext.newInstance(className);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		T objectUnmarshalled = (T) jaxbUnmarshaller.unmarshal(new FileReader(FILEPATH));

		return objectUnmarshalled;
	}
}
