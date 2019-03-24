package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.PersonTagService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.model.entity.PersonTag;


@Path("setPersonTag")
public class servletSetPersonTag {

	@Inject
	private QuestionService questionService;
	@Inject
	private PersonTagService personTagService;

	@Inject
	private PersonService personService;

	/**
	 * Using @FormParam inject form data in method arguments
	 * 
	 * @param idQuestion
	 * @param cookieIdAuthor
	 * @param authorIdFromCookie
	 *            : contains the new authorId that can be null
	 * @return id of modified PersonTag
	 * 
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String setPersonTag(@QueryParam("idQuestion") Integer idQuestion,
			@QueryParam("idAuthor") Integer newIdAuthor, @CookieParam("authorId") Cookie cookieIdAuthor,
			@QueryParam("tag") String tag) throws Exception {
		int QuestionId = Integer.valueOf(idQuestion);

		int newAuthorId;

		if (cookieIdAuthor == null) {
			if (newIdAuthor == null)
				throw new Exception(
						"Both Cookie and the input Author Id's field are null, please log-in or register again.");

			newAuthorId = Integer.valueOf(newIdAuthor);
		} else {
			newAuthorId = Integer.valueOf(cookieIdAuthor.getValue());
		}

		PersonTag personTag = personTagService.findPersonTag(QuestionId);
		PersonTag modifiedTag;
		String subject = personTag.getTag();
		if (subject == null)
			throw new Exception("Subject do not be a null ");
		else
			modifiedTag = new PersonTag(questionService.findQuestion(QuestionId), personService.findPerson(newAuthorId),
					tag);
		personTagService.persist(modifiedTag);
		return String.valueOf(modifiedTag.getIdPersonTag());

	}

}
