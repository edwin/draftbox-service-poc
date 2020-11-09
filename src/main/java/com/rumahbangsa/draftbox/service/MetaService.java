package com.rumahbangsa.draftbox.service;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.agroal.api.AgroalDataSource;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.InsertOneResult;
import com.rumahbangsa.draftbox.model.Metadata;
import com.rumahbangsa.draftbox.util.DateTimeUtil;

@ApplicationScoped
public class MetaService {

    Logger logger = LoggerFactory.getLogger(MetaService.class);

    @Inject
    MongoClient mongoClient;

    public JsonNode checkData(JsonNode header, JsonNode body) {
        MongoCollection coll = mongoClient.getDatabase("test").getCollection("sample_collection");
        MongoCursor<Document> cursor = coll.find().iterator();

        ArrayNode result = JsonNodeFactory.instance.arrayNode();

        Document document;
        while (cursor.hasNext()) {
            document = cursor.next();

            result.add(document.getString("foo"));
        }

        cursor.close();

        return result;
    }

    public JsonNode saveData(JsonNode header, JsonNode body) {
        logger.debug("saveData() header={} body={}", header, body);

        String msg = body.findPath("message").asText();

        Document doc = new Document()
                .append("foo", msg);

        MongoCollection coll = mongoClient.getDatabase("test").getCollection("sample_collection");
        InsertOneResult res = coll.insertOne(doc);

        String insertId = res.getInsertedId().asObjectId().getValue().toString();

        ObjectNode respNode = JsonNodeFactory.instance.objectNode();
        respNode.put("id", insertId);

        return respNode;
    }

    public JsonNode saveMeta(JsonNode header, JsonNode body) {
        String topic = body.findPath("topic").asText();

        Metadata md = new Metadata();
        md.setLastUpdated(LocalDateTime.now());
        md.setTopic(topic);

        md.persist();

        ObjectNode respNode = JsonNodeFactory.instance.objectNode();
        respNode.put("id", md.id.toHexString());

        return respNode;
    }

    public JsonNode getMeta(JsonNode header, JsonNode body) {
        String id = body.findPath("id").asText();

        Metadata md = Metadata.findById(new ObjectId(id));
        ObjectNode respNode = JsonNodeFactory.instance.objectNode();

        if (null != md) {
            respNode.put("id", md.id.toHexString());
            respNode.put("topic", md.getTopic());
            respNode.put("lastUpdated", DateTimeUtil.localDateTimeToString(md.getLastUpdated()));
        }

        return respNode;
    }
}
