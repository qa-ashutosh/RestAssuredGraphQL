package com.qa.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){
        if(dataLoader == null) {  // We are making sure that dataLoader object should be created only once, and we are loading data property only once (using singleton design pattern)
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getGetPlaylistId(){
        String prop = properties.getProperty("getPlaylistId");
        if(prop != null) return prop;
        else throw new RuntimeException("property getPlaylistId is not specified in the data.properties file");
    }

    public String getUpdatePlaylistId(){
        String prop = properties.getProperty("updatePlaylistId");
        if(prop != null) return prop;
        else throw new RuntimeException("property updatePlaylistId is not specified in the data.properties file");
    }
}