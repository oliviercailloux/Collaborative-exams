package controller;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import helper.QuestionText;
import model.entity.data;
import model.entity.question.Question;

@RunWith(Arquillian.class)
public class NewQuestionTest {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(NewQuestionTest.class.getCanonicalName());

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "collaborative-exams-0.0.1.war")
				.addPackage(NewQuestionTest.class.getPackage());
		return war;
	}

	@ArquillianResource
	private URL baseURL;

	@Test
	public void NewQuestionServletTest() throws Exception {
		final Client client = ClientBuilder.newClient();
		final WebTarget target = client.target(baseURL.toString()).path("/v1/NewQuestion/New");
		LOGGER.info(target.getUri().toString());
		
		final String Result = target
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(QuestionText.QuestionToJson(data.getQuestion(0)), MediaType.APPLICATION_JSON), String.class);
		
		assertEquals(data.questionCount,3);
		assertEquals(data.getQuestion(0).getId(), data.getQuestion(data.questionCount-1).getId());
		client.close();
	}
	

}