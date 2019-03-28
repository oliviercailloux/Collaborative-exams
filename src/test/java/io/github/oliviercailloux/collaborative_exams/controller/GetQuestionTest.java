package io.github.oliviercailloux.collaborative_exams.controller;

import java.net.URL;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset; 
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class GetQuestionTest {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(GetQuestionTest.class.getCanonicalName());

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "examcollab.war")
				.addPackage(GetQuestionTest.class.getPackage())
		return war;
	}

	@ArquillianResource
	private URL baseURL;

	@Test
	public void getQuestionServletTest() throws Exception {
		final Client client = ClientBuilder.newClient();
		/*
		 * final WebTarget target =
		 * client.target(baseURL.toString()).path("/rest/Questions/Get/"+1);
		 * 
		 * LOGGER.info(target.getUri().toString());
		 * 
		 * final String Result =
		 * target.request(MediaType.APPLICATION_JSON).get(String.class);
		 * 
		 * assertEquals("Aucune Question n'existe sous cette identifiant", Result);
		 */

		client.close();
	}

}
