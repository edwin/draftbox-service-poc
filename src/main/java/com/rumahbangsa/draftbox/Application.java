package com.rumahbangsa.draftbox;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;

@Path("/api/v1/draftbox")
public class Application {

	Logger log = LoggerFactory.getLogger(Application.class);
	
	@PostConstruct
	public void listen() {
		log.info("--- {} ---", Application.class.getName());
	}
	
	@GET
	@Path("/ping")	
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Ping> ping() {
		return Uni.createFrom().item(() -> new Ping());
	}
}
