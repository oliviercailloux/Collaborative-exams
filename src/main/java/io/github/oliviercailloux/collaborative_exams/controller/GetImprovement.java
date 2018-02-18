package io.github.oliviercailloux.collaborative_exams.controller;

import io.github.oliviercailloux.collaborative_exams.Service.ImprovementService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Improvement;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


/**
 * JAX-RS Servlet that allows to get an improvement relation between two questions according to an author
 */
@Path("Improvement")
public class GetImprovement {

    @Inject
    private ImprovementService improvementService;

    /**
     *
     * @param id represent the id of the Improvement relation
     * @return the relation well formatted in json
     * @throws Exception if the relation is not found
     */
    @Path("/?id={id}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getImprovement(@PathParam("id") int id) throws Exception {
        Improvement improvement = improvementService.findImprovement(id);
        return QuestionText.ObjectToJson(Improvement.class, improvement);
    }

}
