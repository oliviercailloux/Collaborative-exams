package io.github.oliviercailloux.collaborative_exams.controller;

import java.net.URL;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ChangeQuestionLanguaugeTest {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ChangeQuestionLanguaugeTest.class.getCanonicalName());

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "examcollab.war")
				.addPackage(ChangeQuestionLanguaugeTest.class.getPackage())
				.addAsManifestResource(EmptyAsset.INSTANCE, "META-INF/beans.xml");
		return war;
	}

	@ArquillianResource
	private URL baseURL;

	@Test
	public void getQuestionServletTest() throws Exception {
		final Client client = ClientBuilder.newClient();

		/*
		 * WebTarget target =
		 * client.target(baseURL.toString()).path("/rest/ChangeQuestionLanguage");
		 * 
		 * 
		 * MultivaluedMap<String,String> params = new MultivaluedHashMap<>();
		 * params.add("id", "2"); params.add("idAuthor", "1"); params.add("lang",
		 * "English");
		 * 
		 * for(String key : params.keySet()) { target = target.queryParam(key,
		 * params.getFirst(key)); }
		 * 
		 * 
		 * LOGGER.info(target.getUri().toString());
		 * 
		 * final String Result =
		 * target.request(MediaType.APPLICATION_JSON).get(String.class);
		 * 
		 * //assertEquals(QuestionText.QuestionToJson(data.getQuestionByID(2 * 100)),
		 * Result);
		 */
		client.close();
	}

}
