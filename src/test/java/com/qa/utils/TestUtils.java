package com.qa.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.json.JSONObject;

public class TestUtils {

    public static String graphqlToJsonString(String payload){
        JSONObject json = new JSONObject();
        json.put("query", payload);
        return  json.toString();
    }

    public static String  graphqlWithVariablesToJsonString(String payload, JSONObject variables){
        JSONObject json = new JSONObject();
        json.put("query", payload);
        json.put("variables", variables);
        return json.toString();
    }

    public static String graphqlWithVariablesToJsonString(String payload, String variables){
        JSONObject json = new JSONObject();
        json.put("query", payload);
        json.put("variables", variables);
        return json.toString();
    }

    public static JSONObject stringToJSON(String value){
        return new JSONObject(value);
    }

    public static JsonPath rawToJSON(Response res) {

        String response = res.asString();
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }

    public static XmlPath rawToXML(Response res) {

        String response = res.asString();
        XmlPath xmlPath = new XmlPath(response);
        return xmlPath;
    }
}
