package io.github.oliviercailloux.collaborative_exams.helper;

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

import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.data;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

import org.junit.Assert;
import org.junit.Test;

@RunWith(Arquillian.class)
public class QuestionTextTest {
	
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(QuestionTextTest.class.getCanonicalName());

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "collaborative-exams-0.0.1.war")
				.addPackage(QuestionTextTest.class.getPackage());
		return war;
	}

		
    @Test
    public void JsonToQuestionTest() {
}
    
    
    
    @Test
    public void QuestionToJsonTest() {
}
}




