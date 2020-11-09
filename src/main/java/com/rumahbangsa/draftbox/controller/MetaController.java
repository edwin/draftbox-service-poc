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

@Path("/api/v1/draftbox/meta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MetaController {

    @Inject
    MetaService metaService;

    ObjectMapper objMapper = new ObjectMapper();

    @Path("/checkData")
    @POST
    public Uni<JsonNode> checkData(@HeaderParam("X-Consumer-Custom-ID") String customId, JsonNode requestBody) throws Exception {
        JsonNode headerNode = objMapper.readTree(customId);

        return Uni.createFrom().item(() -> {
            return metaService.checkData(headerNode, requestBody);
        });
    }

    @Path("/saveData")
    @POST
    public Uni<JsonNode> saveData(@HeaderParam("X-Consumer-Custom-ID") String customId, JsonNode requestBody) throws Exception {
        JsonNode headerNode = objMapper.readTree(customId);

        return Uni.createFrom().item(() -> {
            return metaService.saveData(headerNode, requestBody);
        });
    }

    @Path("/saveMeta")
    @POST
    public Uni<JsonNode> saveMeta(@HeaderParam("X-Consumer-Custom-ID") String customId, JsonNode requestBody) throws Exception {
        JsonNode headerNode = objMapper.readTree(customId);

        return Uni.createFrom().item(() -> {
            return metaService.saveMeta(headerNode, requestBody);
        });
    }

    @Path("/getMeta")
    @POST
    public Uni<JsonNode> getMeta(@HeaderParam("X-Consumer-Custom-ID") String customId, JsonNode requestBody) throws Exception {
        JsonNode headerNode = objMapper.readTree(customId);

        return Uni.createFrom().item(() -> {
            return metaService.getMeta(headerNode, requestBody);
        });
    }
}
