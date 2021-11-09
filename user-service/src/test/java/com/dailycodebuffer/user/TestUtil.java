package com.dailycodebuffer.user;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TestUtil
{
    public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz) throws IOException {
        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, clazz);
    }
}
