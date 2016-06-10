package com.weixin.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 */
public class PropertyReader {

    private String fileName;

    private Properties properties;

    public PropertyReader(String fileName) {
        this.fileName = fileName;
        Resource resource = new ClassPathResource("/" + fileName);
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean getBoolean(String key) {
        Object result = properties.get(key);
        if (result == null) {
            return null;
        }
        if (result.toString().equalsIgnoreCase("true")) {
            return true;
        }
        if (result.toString().equalsIgnoreCase("false")) {
            return false;
        }
        return null;
    }


    public String getString(String key) {
        Object result = properties.get(key);
        if (result == null || "".equals(result)) {
            return null;
        } else {
            return result.toString();
        }
    }

    public Integer getInteger(String key) {
        Object result = properties.get(key);
        if (result == null || "".equals(result)) {
            return null;
        } else {
            return Integer.valueOf(getString(key));
        }
    }

}

