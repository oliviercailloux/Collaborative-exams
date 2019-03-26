package io.github.oliviercailloux.collaborative_exams.helper;

import java.util.logging.Logger;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class QuestionTextTest {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(QuestionTextTest.class.getCanonicalName());

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class, "examcollab.war")
				.addPackage(QuestionTextTest.class.getPackage())
				.addAsManifestResource(EmptyAsset.INSTANCE, "META-INF/beans.xml");
		return war;
	}

	@Test
	public void JsonToQuestionTest() {
	}

	@Test
	public void QuestionToJsonTest() {
	}
}
