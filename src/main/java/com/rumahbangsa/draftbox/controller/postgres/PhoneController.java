package com.rumahbangsa.draftbox.controller.postgres;


import com.rumahbangsa.draftbox.model.postgres.PhoneData;
import com.rumahbangsa.draftbox.service.posgres.PhoneService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PhoneController {

    @Inject
    PhoneService phoneService;


    @Path("/checkData")
    @GET
    public Uni<PhoneData> saveTest() throws Exception {

        return Uni.createFrom().item(() -> {
            return phoneService.findAll();
        });
    }

}
