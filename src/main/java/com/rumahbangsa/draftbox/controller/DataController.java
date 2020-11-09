package com.rumahbangsa.draftbox.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rumahbangsa.draftbox.service.MetaService;

import io.smallrye.mutiny.Uni;

@Path("/api/v1/draftbox/data")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DataController {

    @Inject
    MetaService metaService;

    ObjectMapper objMapper = new ObjectMapper();

    @Path("/all")
    @POST
    public Uni<JsonNode> getAll(@HeaderParam("X-Consumer-Custom-ID") String customId, JsonNode requestBody) throws Exception {
        // TODO get all data from respective owner
        JsonNode headerNode = objMapper.readTree(customId);

        return Uni.createFrom().item(() -> {
            return null;
        });
    }

    @Path("/range")
    @POST
    public Uni<JsonNode> getRange(@HeaderParam("X-Consumer-Custom-ID") String customId, JsonNode requestBody) throws Exception {
        // TODO get all data from respective owner
        JsonNode headerNode = objMapper.readTree(customId);

        return Uni.createFrom().item(() -> {
            return null;
        });
    }

    @Path("/")
    @POST
    public Uni<JsonNode> getData(@HeaderParam("X-Consumer-Custom-ID") String customId, JsonNode requestBody) throws Exception {
        // TODO get a single data
        JsonNode headerNode = objMapper.readTree(customId);

        return Uni.createFrom().item(() -> {
            return null;
        });
    }

    @Path("/put")
    @POST
    public Uni<JsonNode> putData(@HeaderParam("X-Consumer-Custom-ID") String customId, JsonNode requestBody) throws Exception {
        // TODO put data as draft
        JsonNode headerNode = objMapper.readTree(customId);

        return Uni.createFrom().item(() -> {
            return null;
        });
    }

}
