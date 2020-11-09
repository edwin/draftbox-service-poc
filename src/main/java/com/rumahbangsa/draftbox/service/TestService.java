package com.rumahbangsa.draftbox.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rumahbangsa.draftbox.model.Metadata;
import com.rumahbangsa.draftbox.model.TestData;
import com.rumahbangsa.draftbox.util.DateTimeUtil;
import io.quarkus.panache.common.Sort;
import io.quarkus.scheduler.Scheduled;
import io.vertx.core.json.JsonObject;
import org.bson.types.ObjectId;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TestService {


    Logger logger = LoggerFactory.getLogger(MetaService.class);

    public JsonNode saveTest(JsonNode header, JsonNode body) {

        JsonNode content = body.findPath("content");
        JsonNode userid = header.findPath("userId");
        ObjectNode respNode = JsonNodeFactory.instance.objectNode();

        try {
            Integer version = this.getNextVersion();
            TestData test = new TestData();
            test.setContent(content);
            test.setVersion(version);

            test.persist();

            respNode.put("id", test.getId().toHexString());
            respNode.put("content", test.getContent());
            respNode.put("version", test.getVersion());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            respNode.put("error", e.getMessage());
        }
        return respNode;
    }


    public TestData getTest(JsonNode header, String Id) {

        TestData td = TestData.findById(new ObjectId(Id));

//        if (null != td) {
//            respNode.put("id", td.id.toHexString());
//            respNode.put("content", td.getContent());
//            respNode.put("version", td.getVersion());
//        }

        return td;
    }


    private int getNextVersion() {
        TestData test = TestData.find("version > ?1",
                Sort.by("version").descending(), 0).firstResult();

        if (test == null) return 1;

        return test.getVersion() + 1;
    }

}
