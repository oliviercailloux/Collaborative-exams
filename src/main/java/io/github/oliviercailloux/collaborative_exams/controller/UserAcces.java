package io.github.oliviercailloux.collaborative_exams.controller;

import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import io.github.oliviercailloux.collaborative_exams.Service.PersonService;
import io.github.oliviercailloux.collaborative_exams.model.entity.Person;

@Path("user")
public class UserAcces {

	@Inject
	PersonService personService;

	private java.net.URI location;

	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	public Response register(@CookieParam("authorId") String authorId, @FormParam("email") String email)
			throws Exception {
		location = new java.net.URI("../index.html");
		// new registration
		if (email == null)
			throw new Exception("Email est null !");

		if (authorId == null) {
			Person author = new Person(email);
			personService.persist(author);
			NewCookie cookie = new NewCookie("authorId", String.valueOf(author.getId()), "/", "", "comment", 100000,
					false);
			return Response.temporaryRedirect(location).cookie(cookie).build();
		}
		// user already logged-in
		return Response.temporaryRedirect(location).build();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(@CookieParam("authorId") String authorId, @FormParam("email") String email) throws Exception {

		if (email == null)
			throw new Exception("Email est null !");
		location = new java.net.URI("../index.html");
		// new connection
		if (authorId == null) {
			Person author = personService.findPersonByEmail(email);
			NewCookie cookie = new NewCookie("authorId", String.valueOf(author.getId()), "/", "", "comment", 100000,
					false);
			return Response.temporaryRedirect(location).cookie(cookie).build();
		}
		// user already logged-in
		return Response.temporaryRedirect(location).build();
	}

	@GET
	@Path("/logout")
	@Produces(MediaType.TEXT_PLAIN)
	public Response logout(@CookieParam("authorId") Cookie cookie) throws URISyntaxException {
		location = new java.net.URI("../index.html");
		if (cookie != null) {
			NewCookie newCookie = new NewCookie(cookie, null, 0, false);
			return Response.ok("OK - Session Destroyed : " + cookie.getValue()).cookie(newCookie).build();
		}
		return Response.ok("OK - No session started").build();
	}

	@GET
	@Path("/getCookie")
	@Produces(MediaType.TEXT_PLAIN)
	public Response foo(@CookieParam("authorId") String authorId) {
		if (authorId == null) {
			return Response.serverError().entity("ERROR").build();
		}
		return Response.ok(authorId).build();
	}
}
