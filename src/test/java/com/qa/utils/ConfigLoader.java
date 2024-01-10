package com.qa.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null) {  // We are making sure that configLoader object should be created only once, and we are loading config property only once (using singleton design pattern)
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId(){
        String prop = properties.getProperty("client_id");
        if(prop != null) return prop;
        else throw new RuntimeException("Property client_id is not specified in the config.properties file");
    }

    public String getClientSecret(){
        String prop = properties.getProperty("client_secret");
        if(prop != null) return prop;
        else throw new RuntimeException("Property client_secret is not specified in the config.properties file");
    }

    public String getGrantType(){
        String prop = properties.getProperty("grant_type");
        if(prop != null) return prop;
        else throw new RuntimeException("Property grant_type is not specified in the config.properties file");
    }

    public String getRefreshToken(){
        String prop = properties.getProperty("refresh_token");
        if(prop != null) return prop;
        else throw new RuntimeException("Property refresh_token is not specified in the config.properties file");
    }

    public String getUser(){
        String prop = properties.getProperty("user_id");
        if(prop != null) return prop;
        else throw new RuntimeException("Property user_id is not specified in the config.properties file");
    }

    public String getBaseUri(){
        String prop = properties.getProperty("base_uri");
        if(prop != null) return prop;
        else throw new RuntimeException("Property base_uri is not specified in the config.properties file");
    }

    public String getAccountBaseUri(){
        String prop = properties.getProperty("account_base_uri");
        if(prop != null) return prop;
        else throw new RuntimeException("Property account_base_uri is not specified in the config.properties file");
    }

    public String getGraphQlUri(){
        String prop = properties.getProperty("graphql_base_uri");
        if(prop != null) return prop;
        else throw new RuntimeException("Property graphql_base_uri is not specified in the config.properties file");
    }

    public String getUserAccountBaseUri(){
        String prop = properties.getProperty("user_account_base_uri");
        if(prop != null) return prop;
        else throw new RuntimeException("Property user_account_base_uri is not specified in the config.properties file");
    }

}