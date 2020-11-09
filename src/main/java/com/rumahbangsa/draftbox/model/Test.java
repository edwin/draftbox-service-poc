package com.rumahbangsa.draftbox.model;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;


@MongoEntity(collection="test_collection")
public class Test extends PanacheMongoEntity {
    String content;
    Integer version;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
