package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import helper.QuestionText;
import model.entity.data;
import model.entity.question.Question;

@Path("GetSameAbility")
public class GetSameAbility {

	@Path("Get")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getSameAbility(@QueryParam("idQ1") int id_q1,@QueryParam("idQ2") int id_q2) throws Exception {
		
		return data.isSameAbilities(id_q1, id_q2);
	}
}
