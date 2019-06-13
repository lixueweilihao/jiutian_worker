package com.play.http.client;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class JsonUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> List<T> getObjectsFromJson(InputStream instream, Class<T> clsT) throws Exception {
        List<T> list = new LinkedList<T>();
        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonParser parser = jsonFactory.createParser(instream);

            list = objectMapper.readValue(parser, new TypeReference<List<T>>() {
            });

        } catch (JsonParseException e) {
            throw new Exception("parse json error", e);

        } finally {
            try {
                instream.close();

            } catch (Exception ignore) {
            }
        }
        return list;
    }

    public static <T> List<T> getObjectsFromJson(String str, Class<T> clsT) throws Exception {
        List<T> list = new LinkedList<T>();
        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonParser parser = jsonFactory.createParser(str);

            list = objectMapper.readValue(parser, new TypeReference<List<T>>() {
            });

        } catch (JsonParseException e) {
            throw new Exception("parse json error, json=" + str +
                    ", class=" + clsT.getName(), e);

        } catch (IOException e) {
            throw new Exception("parse json error, json=" + str +
                    ", class=" + clsT.getName(), e);
        }
        return list;
    }

    public static <T> T getObjectFromJson(String str, TypeReference<T> typeReference) throws Exception {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser parser = jsonFactory.createParser(str);
            return objectMapper.readValue(parser, typeReference);
        } catch (JsonParseException e) {
            throw new Exception("parse json error, json=" + str +
                    ", type=" + typeReference.getType(), e);

        } catch (IOException e) {
            throw new Exception("parse json error, json=" + str +
                    ", type=" + typeReference.getType(), e);
        }
    }

    public static <T> T getObjectFromJson(InputStream instream, Class<T> cls) throws Exception {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser parser = jsonFactory.createParser(instream);
            T t = objectMapper.readValue(parser, cls);
            return t;

        } catch (JsonParseException e) {
            throw new Exception("parse json error", e);

        } catch (IOException e) {
            throw new Exception("parse json error", e);

        } finally {
            try {
                instream.close();

            } catch (Exception ignore) {

            }
        }
    }

    public static <T> T getObjectFromJson(String str, Class<T> cls) throws Exception {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser parser = jsonFactory.createParser(str);
            T t = objectMapper.readValue(parser, cls);
            return t;

        } catch (JsonParseException e) {
            throw new Exception("parse json error, json=" + str +
                    ", class=" + cls.getName(), e);

        } catch (IOException e) {
            throw new Exception("parse json error, json=" + str +
                    ", class=" + cls.getName(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public static String getValueByFieldFromJson(String json, String field) throws Exception {
        Map<String, String> mapValue = getObjectFromJson(json, HashMap.class);
        return String.valueOf(mapValue.get(field));
    }

    public static String getJsonFromObject(Object object) throws Exception {
        try {
            return objectMapper.writeValueAsString(object);

        } catch (JsonGenerationException e) {
            throw new Exception("get json error", e);

        } catch (JsonMappingException e) {
            throw new Exception("get json error", e);

        } catch (IOException e) {
            throw new Exception("get json error", e);
        }
    }

    public static String getJsonWithoutNullFromObject(Object object) throws Exception {
        try {
            ObjectMapper om = new ObjectMapper();
            om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            om.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
            return om.writeValueAsString(object);

        } catch (JsonGenerationException e) {
            throw new Exception("get json error", e);

        } catch (JsonMappingException e) {
            throw new Exception("get json error", e);

        } catch (IOException e) {
            throw new Exception("get json error", e);
        }
    }

}
