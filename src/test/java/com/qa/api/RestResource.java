package com.qa.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.qa.api.Route.*;
import static com.qa.api.SpecBuilder.*;
import static com.qa.api.TokenManager.getGraphQLToken;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, String token, Object requestPlaylist){
        return given(getRequestSpec())
                .auth().oauth2(token)
                .body(requestPlaylist)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path, String token){
        return given(getRequestSpec())
                .auth().oauth2(token)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response put(String path, String token, Object requestPlaylist){
        return given(getRequestSpec())
                .auth().oauth2(token)
                .body(requestPlaylist)
                .when().put(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response postAccount(HashMap<String, String> formParams){
        return given(getAccountRequestSpec())
                .formParams(formParams)
                .when().post(API + TOKEN)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response post(String path, Object requestPayload){
        return given(getUserAccountRequestSpec())
                .body(requestPayload)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response postUserAccount(String payload){
        return given(getUserAccountRequestSpec())
//                .filter((req, res, ctx) -> {
//                    if(req.getBody() != null) {
//                        String body = req.getBody().toString();
//                        System.out.println("Req-Body: " + body);
////                        body = body.replaceAll("\"password\": \"[^\"]*\"", "\"password\": \"*****\"");
////                        body = body.replaceAll("\"password\": \"Test@1234\"", "\"password\": \"*****\"");
//                        System.out.println("After conversion Req-Body: " + body);
//                        req.body(body);
//                    }
//                    return ctx.next(req, res);
//                })
                .body(payload)
                .when().post(ACCOUNT_BASE_PATH + SIGN_IN)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response sendAPIRequest(String payload) {
        EncoderConfig encoderConfig = new EncoderConfig();

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + getGraphQLToken())
                .config(RestAssured.config()
                        .logConfig(LogConfig.logConfig().blacklistHeader("Authorization"))
                        .encoderConfig(encoderConfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)
                                .encodeContentTypeAs("application/graphql", ContentType.TEXT)))
                .filter(new AllureRestAssured())
                .log().all()
                .body(payload)
                .when()
//                .post(baseURI)
//                .post("https://localhost:3001/api/graphql")
                .post("https://xervg54662.execute-api.us-west-2.amazonaws.com/Prod/api/graphql")
//                .post ("https://rahulshettyacademy.com/gq/graphql")
                .then()
                .assertThat()
                .statusCode(200)
                .and().contentType(ContentType.JSON)
                .extract().response().prettyPeek();

        return response;
    }
}
