package com.rumahbangsa.draftbox.model;

import java.time.Instant;

import com.fasterxml.jackson.databind.JsonNode;
import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;


@MongoEntity(collection = "schedule")
public class TaskData extends PanacheMongoEntity {

    public Instant createdAt;


    public TaskData() {
        createdAt = Instant.now();
    }

    public TaskData(Instant time, JsonNode testval) {
        this.createdAt = time;

    }
}
