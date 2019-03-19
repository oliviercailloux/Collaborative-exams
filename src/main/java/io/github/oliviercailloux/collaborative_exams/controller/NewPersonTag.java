package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.Service.PersonTagService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.PersonTag;

@Path("NewPersonTag")
public class NewPersonTag {
	@Inject
	private QuestionService questionService;

	@Inject
	private PersonService personService;

	@Inject
	private PersonTagService personTagService;

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String newPersonTag(MultivaluedMap<String, String> form, @CookieParam("authorId") Cookie cookie)
			throws Exception {

		Integer idQuestion = Integer.valueOf(form.getFirst("idQuestion"));
		Integer idAuthor = Integer.valueOf(form.getFirst("idAuthor"));
		String tag = form.getFirst("tag");

		int AuthorId;

		if (cookie == null) {
			if (idAuthor == null)

				throw new Exception(
						"Both Cookie and the input Author Id's field are null, please log-in or register again.");

			AuthorId = Integer.valueOf(form.getFirst("idAuthor"));
		} else {
			AuthorId = Integer.valueOf(cookie.getValue());
		}

		if (personTagService.findPersonTag(questionService.findQuestion(idQuestion),
				personService.findPerson(idAuthor)) != null)
			throw new Exception(
					"there is already a tag associated with the tuple question:" + idQuestion + "Author :" + idAuthor);
		if (idQuestion == null)
			throw new Exception("you can not give a tag for a question that does not exist");
		if (tag == null)
			throw new Exception("the tag must not be null!");

		PersonTag personTag = new PersonTag(questionService.findQuestion(idQuestion),
				personService.findPerson(idAuthor), tag);
		personTagService.persist(personTag);

		return QuestionText.ObjectToJson(PersonTag.class, personTag);

	}

}
