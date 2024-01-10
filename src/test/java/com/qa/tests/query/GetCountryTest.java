package com.qa.tests.query;

import com.qa.api.RestResource;
import com.qa.graphql_payload.QueryPayload;
import com.qa.tests.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.qa.utils.TestUtils.rawToJSON;

public class GetCountryTest extends BaseTest {

    @Test(description = "Get Country Test")
    public void getAllCountry() {

        String payload = QueryPayload.getCountry();
        Response res = RestResource.sendAPIRequest(payload);

        JsonPath js = rawToJSON(res);
        var data = js.get("countries");
        System.out.println("GraphQL response: " + data);
    }

    @Test(description = "Get Country By Id Test")
    public void getCountryById() {
        String countryId = "05fd58b8-3484-40a4-b6dc-006669a5161e";

        String payload = QueryPayload.getCountryById(countryId);
        Response res = RestResource.sendAPIRequest(payload);

        JsonPath js = rawToJSON(res);
        var data = js.get("country");
        System.out.println("GraphQL response: " + data);
    }

    @Test(description = "Get Character By Id Test")
    public void getCharacterById() {
        String payload = QueryPayload.getCharacterById();
        Response res = RestResource.sendAPIRequest(payload);

        System.out.println("----res----" + res.asString());
        JsonPath js = rawToJSON(res);
        var data = js.get("data");
        System.out.println("GraphQL response: " + data);
    }

//    @Test(description = "Get Characters By FilterTest")
//    public void getCharactersByFilter() {
//
//        String payload = QueryPayload.getCharacters();
//        Response res = RestResource.sendAPIRequest(payload);
//
//        JsonPath js = rawToJSON(res);
//        var data = js.get("data");
//        System.out.println("GraphQL response: " + data);
//    }
}
