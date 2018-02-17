package io.github.oliviercailloux.collaborative_exams.controller;

import io.github.oliviercailloux.collaborative_exams.Service.ImprovementService;
import io.github.oliviercailloux.collaborative_exams.Service.QuestionService;
import io.github.oliviercailloux.collaborative_exams.helper.QuestionText;
import io.github.oliviercailloux.collaborative_exams.model.entity.Improvement;
import io.github.oliviercailloux.collaborative_exams.model.entity.question.Question;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("Improvement")
public class GetImprovement {

    @Inject
    ImprovementService improvementService;


    @Path("/{id}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getImprovement(@PathParam("id") int id) throws Exception {
        Improvement improvement = improvementService.findImprovement(id);
        return QuestionText.ObjectToJson(Improvement.class,improvement);
    }

}
