package com.qa.api;

import com.qa.utils.ConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import static com.qa.api.Route.BASE_PATH;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("BASE_URI", ConfigLoader.getInstance().getBaseUri()))  //It will look for the base_uri from command line initially, if not provided, then it take the default value from config properties
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getAccountRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("ACCOUNT_BASE_URI", ConfigLoader.getInstance().getAccountBaseUri())) //It will look for the account_base_uri from command line initially, if not provided, then it take the default value from config properties
                .setContentType(ContentType.URLENC)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getUserAccountRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("USER_ACCOUNT_BASE_URI", ConfigLoader.getInstance().getUserAccountBaseUri())) //It will look for the account_base_uri from command line initially, if not provided, then it take the default value from config properties
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }
}
