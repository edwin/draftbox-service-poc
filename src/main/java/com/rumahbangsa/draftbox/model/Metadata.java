package com.rumahbangsa.draftbox.model;

import java.time.LocalDateTime;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection="sample_collection")
public class Metadata extends PanacheMongoEntity {

	LocalDateTime lastUpdated;
	
	String topic;

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
