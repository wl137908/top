package com.weixin.util;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Jackson 映射工具类
 */
public class Jackson {

    static ObjectMapper mapper = new ObjectMapper();

    static {
        // SerializationFeature for changing how JSON is written	序列化参数

        // to enable standard indentation ("pretty-printing"):	整理为漂亮的格式
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // to allow serialization of "empty" POJOs (no properties to serialize)
        // (without this setting, an exception is thrown in those cases)	允许序列化没有属性的类
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // to write java.util.Date, Calendar as number (timestamp):	把时间格式化为时间戳
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


        // DeserializationFeature for changing how JSON is read as POJOs:

        // to prevent exception when encountering unknown property:	遇到类里不存在的属性报错
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // to allow coercion of JSON empty String ("") to null Object value:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);


        // JsonParser.Feature for configuring parsing settings:

        // to allow C/C++ style comments in JSON (non-standard, disabled by default)
        mapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
        // to allow (non-standard) unquoted field names in JSON:
        mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        // to allow use of apostrophes (single quotes), non standard
        mapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);

        // JsonGenerator.Feature for configuring low-level JSON generation:

        // to force escaping of non-ASCII characters:
        mapper.disable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
    }

    public static String asString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}

