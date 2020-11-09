package com.rumahbangsa.draftbox.scheduler;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.rumahbangsa.draftbox.model.TaskData;
import com.rumahbangsa.draftbox.model.TestData;
import com.rumahbangsa.draftbox.scheduler.db.Connect;
import io.quarkus.scheduler.Scheduled;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

public class TaskScheduler {


    @Transactional
    @Scheduled(every = "24h")
    void Task(){
     //  Connect.connect("testSqlite.db");
    }
}
