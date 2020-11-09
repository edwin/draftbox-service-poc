package com.rumahbangsa.draftbox.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.rumahbangsa.draftbox.model.TestData;
import com.rumahbangsa.draftbox.service.MetaService;
import org.bson.*;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;


public class TestDataCodec implements CollectibleCodec<TestData>
{
    Logger logger = LoggerFactory.getLogger(MetaService.class);

    private final Codec<Document> documentCodec;

    public TestDataCodec()
    {
        this.documentCodec = MongoClientSettings.getDefaultCodecRegistry().get(Document.class);
    }


    @Override
    public void encode(BsonWriter writer, TestData testData, EncoderContext encoderContext)
    {
        Document doc = new Document();

        if(testData.getContent() != null)
        {
            BasicDBObject content = new BasicDBObject();
            try
            {
                var keyValuePairs = new ObjectMapper()
                        .readValue(testData.getContent().traverse(), HashMap.class);

                content.putAll(keyValuePairs);

                doc.put("content", content);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        doc.put("version", testData.getVersion());
        doc.put("_id",testData.getId());


        this.documentCodec.encode(writer, doc, encoderContext);
    }

    @Override
    public Class<TestData> getEncoderClass()
    {
        return TestData.class;
    }

    @Override
    public TestData generateIdIfAbsentFromDocument(TestData testData)
    {
        if (!documentHasId(testData)){
            ObjectId uid = new ObjectId(UUID.randomUUID().toString().replaceAll("-", "").substring(8));

            testData.setId(uid);
        }
        logger.info("id input: {}", testData);


        return testData;
    }

    @Override
    public boolean documentHasId(TestData testData)
    {

        boolean data = false;
        if (testData.getId() != null){
            data = true;
        }
        logger.info("id has: {}", data);
        return data;
    }

    @Override
    public BsonValue getDocumentId(TestData testData)
    {
        return new BsonString(testData.getId().toHexString());
    }

    @Override
    public TestData decode(BsonReader reader, DecoderContext decoderContext)
    {
        final ObjectMapper mapper = new ObjectMapper();

        Document document = documentCodec.decode(reader, decoderContext);
        TestData data = new TestData();

        Document content = document.get("content", Document.class);
//
//        if (document.getString("id") != null)
//        {
//            data.setId(new ObjectId(document.getString("id")));
//        }

        data.setId(document.getObjectId("_id"));
        data.setVersion(document.getInteger("version"));

        try
        {
            if(content != null)
            {
                data.setContent(mapper.readTree(content.toJson()));
            }
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }

        return data;
    }


}
