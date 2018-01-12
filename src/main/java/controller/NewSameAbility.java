package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import helper.QuestionText;
import model.entity.data;
import model.entity.question.Question;
import model.entity.question.SameAbility;



@Path("NewSameAbility")
public class NewSameAbility {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	public int newSameAbility(@QueryParam("id_q1") int id_q1,@QueryParam("id_q2") int id_q2,@QueryParam("id_p") int id_p) throws Exception {

		SameAbility s = new SameAbility(data.sameAbilityCount+1,data.getQuestionByID(id_q1),data.getQuestionByID(id_q2),data.getAuthorByID(id_p));
		
		data.addSameAbility(s);	
		return data.sameAbilityCount;
		
	}

}
