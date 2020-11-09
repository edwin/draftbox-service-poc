package com.rumahbangsa.draftbox.model;

import com.fasterxml.jackson.databind.JsonNode;
import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@MongoEntity(collection = "test_collection")
public class TestData extends PanacheMongoEntity {

    public ObjectId id;

    public ObjectId getId() {
        return super.id;
    }

    public ObjectId setId(ObjectId id) {
        super.id = id;
        return id;
    }

    private JsonNode content;
    private Integer version;


    public JsonNode getContent() {
        return content;
    }

    public void setContent(JsonNode content) {
        this.content = content;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
