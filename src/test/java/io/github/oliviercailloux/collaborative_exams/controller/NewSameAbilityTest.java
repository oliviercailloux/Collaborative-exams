package io.github.oliviercailloux.collaborative_exams.controller;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.oliviercailloux.collaborative_exams.controller.ServletAbilities.NewSameAbility;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

@RunWith(Arquillian.class)
public class NewSameAbilityTest {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(NewSameAbility.class.getCanonicalName());

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "collaborative-exams-0.0.1.war")
				.addPackage(NewSameAbility.class.getPackage());
		return war;
	}

	@ArquillianResource
	private URL baseURL;

	@Test
	public void NewSameAbilityServletTest() throws Exception {
		final Client client = ClientBuilder.newClient();
		WebTarget target = client.target(baseURL.toString()).path("/rest/NewSameAbility");
		
		MultivaluedMap<String,String> params = new MultivaluedHashMap<>();
		params.add("id_q1","1");
		params.add("id_q2","2");
		params.add("id_p", "1");
		
		for(String key : params.keySet()) {				
			target = target.queryParam(key, params.getFirst(key));
		}	
		

		final String Result = target.request(MediaType.TEXT_PLAIN).get(String.class);
		
		assertEquals(1, Integer.parseInt(Result));
		client.close();
		
		
	
	}


}