package io.github.oliviercailloux.collaborative_exams.controller;

import java.net.URL;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset; 
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class NewQuestionTest {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(NewQuestionTest.class.getCanonicalName());

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "examcollab.war")
				.addPackage(NewQuestionTest.class.getPackage())

		return war;
	}

	@ArquillianResource
	private URL baseURL;

	@Test
	public void NewQuestionServletTest() throws Exception {
		final Client client = ClientBuilder.newClient();
		final WebTarget target = client.target(baseURL.toString()).path("/rest/Add");
		LOGGER.info(target.getUri().toString());

		MultivaluedHashMap<String, String> formValues = new MultivaluedHashMap<>();

		int id = 9;
		String phrasing = "Est ce que 2 * 4 = 12 ?";
		String language = "Francais";
		int author = 1;
		String type = "YN";
		boolean isCorrect = false;

		formValues.add("id", Integer.toString(id));
		formValues.add("phrasing", phrasing);
		formValues.add("language", language);
		formValues.add("author", "1");
		formValues.add("type", type);
		formValues.add("isCorrect", Boolean.toString(isCorrect));

		// int id, String phrasing, String language, Person author, QuestionType type,
		// boolean isCorrect
		// final String result = target.request().post(Entity.form(formValues),
		// String.class);

		// Question questionCreatedFromForm = data.getQuestionByID(id);
		// Question questionCreatedFromValues = new Question(id, phrasing, language,
		// data.getAuthorByID(author),
		// QuestionType.YN, isCorrect);

		// assertEquals(true,
		// questionCreatedFromForm.equalz(questionCreatedFromValues));

		// System.out.println(QuestionText.QuestionToJson(questionCreatedFromForm));
		// System.out.println(QuestionText.QuestionToJson(questionCreatedFromValues));

		/*
		 * final String ResultID = target.request(MediaType.TEXT_PLAIN).post(
		 * Entity.entity(QuestionText.QuestionToJson(data.getQuestionByID(1)),
		 * MediaType.APPLICATION_JSON), String.class);
		 */
		// System.out.println(result);
		client.close();
	}

}
