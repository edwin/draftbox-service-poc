package com.rumahbangsa.draftbox.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rumahbangsa.draftbox.model.TestData;
import com.rumahbangsa.draftbox.service.MetaService;
import com.rumahbangsa.draftbox.service.TestService;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.annotations.Param;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestController {

    @Inject
    TestService testService;

    ObjectMapper objMapper = new ObjectMapper();

    @Path("/saveTest")
    @POST
    public Uni<JsonNode> saveTest(@HeaderParam("X-Consumer-Custom-ID") String customId, JsonNode requestBody) throws Exception {
        JsonNode headerNode = objMapper.readTree(customId);

        return Uni.createFrom().item(() -> {
            return testService.saveTest(headerNode, requestBody);
        });
    }


    @Path("/testData/{id}")
    @GET
    public Uni<TestData> getData(@HeaderParam("X-Consumer-Custom-ID") String customId, @PathParam("id") String Id) throws Exception {
        JsonNode headerNode = objMapper.readTree(customId);

        return Uni.createFrom().item(() -> {
            return testService.getTest(headerNode, Id);
        });
    }
}
