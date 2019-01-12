package io.github.oliviercailloux.collaborative_exams.helper;



import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.dom4j.Element;
import org.dom4j.Document;

import io.github.oliviercailloux.collaborative_exams.model.entity.Person;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.QuestionType;

/**
 * @author Mohamed 
 */
public class QuestionXMLTest {
	
	private Question question;
	private Person person ;
	private QuestionType questionType;
	private String language = "Francais";
	private String parshing= "Est ce que 2 * 4 = 12 ?";
	private QuestionXML questionXml;
	private String type = "TF";
	
	
	  @Before
	    public void setUp() {
		    person = new Person("Personne@outlook.fr");
            question = new Question(parshing,language, person,questionType.TF,true);
	    }
	 
	    @After
	    public void tearDown() throws FileNotFoundException, JAXBException {
	    	question = QuestionXML.unmarshallerXml(question, "questionXml");
	    }
	     

	    @Test
	    public void testObjectToXml() throws NullPointerException, IllegalArgumentException, Exception {
	    	QuestionXML.QuestionToXML(question, "questionXMl");
	    	File fic = new File("questionXMl");
	    	SAXReader reader =  new SAXReader() ;
	    	 /**
	    	  *  lecture de ce fichier à l'aide de ce reader, et construction d'un objet Document
	    	  */
	    	
	    	Document doc = reader.read(fic) ;
	    	
	    	/**
	    	 *  construction de l'élément racine du document XML
	    	 * 
	    	 */
	    	Element root = doc.getRootElement() ;

	    	 /** 
	    	  * lecture des attributs de l'élément racine
	    	  * 
	    	  */
	    	List<Element> attributes = root.attributes() ;

	    	 /**
	    	  *  lecture des sous-élément de la racine
	    	  * 
	    	  */
	    	List<Element> elements = root.elements() ;
	    	assertEquals(root.elementText("phrasing"),this.parshing);
	    	assertEquals(root.elementText("language"),this.language);
	    	assertEquals(root.elementText("type"),this.type);
	    	
	    	
	    	//System.out.println(attributes.get(0)+"\n");
	    	//System.out.println(elements.get(0)+"\n");
	 
	    

	    }
}
