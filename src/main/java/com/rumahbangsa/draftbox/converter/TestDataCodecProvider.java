package com.rumahbangsa.draftbox.converter;

import com.rumahbangsa.draftbox.model.TestData;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class TestDataCodecProvider implements CodecProvider
{
    @Override
    public <T> Codec<T> get(Class<T> aClass, CodecRegistry codecRegistry)
    {
        if (aClass == TestData.class) {
            return (Codec<T>) new TestDataCodec();
        }
        return null;
    }
}
