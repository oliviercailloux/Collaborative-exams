package io.github.oliviercailloux.collaborative_exams.controller;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.github.oliviercailloux.collaborative_exams.Service.PersonService;

@Path("DeletePerson")
public class DeletPerson {

	@Inject
	private PersonService personService;

	@Path("allPerson")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response PersonDeleted() throws Exception {
		personService.deletAllPersons();
		return Response.ok(Response.Status.OK).build();

	}

	@Path("/byId")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response personDeletedById(@QueryParam("id") int id) throws Exception {
		personService.deletPersonById(id);
		return Response.ok(Response.Status.OK).build();
	}

}
