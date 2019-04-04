package io.github.oliviercailloux.collaborative_exams.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.github.oliviercailloux.collaborative_exams.Service.PersonTagService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.PersonTag;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

/**
 * JAX-RS Servlet that allows to get person tags
 */
@Path("PersonTag")
public class GetPersonalTag {

	@Inject
	private PersonTagService persontagservice;
	
	@Inject
	QuestionService questionService;

	/**
	 * @return
	 * @throws Exception
	 */
	@Path("/all")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getPersonTag() throws Exception {
		List<PersonTag> tags = persontagservice.getAll();
		String result = "";
		for (PersonTag p : tags)
			result += QuestionText.ObjectToJson(PersonTag.class, p);
		return result;
	}

	/**
	 * @param id of personTag
	 * @return tag of a question
	 * @throws Exception
	 */
	@Path("/{id}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getPersonTag(@PathParam("id") int id) throws Exception {
		PersonTag tags = persontagservice.findPersonTag(id);
		return QuestionText.ObjectToJson(PersonTag.class, tags);
	}

	/**
	 * @param id of a question
	 * @return tag of a question
	 * @throws Exception
	 */
	@Path("tagQuestion")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getPersonalTag(@QueryParam("idQuestion") int idQuestion) throws Exception {
		Question question = questionService.findQuestion(idQuestion);
		PersonTag tag = persontagservice.findPersonTag(question);
		return QuestionText.ObjectToJson(PersonTag.class, tag);
	}
}
