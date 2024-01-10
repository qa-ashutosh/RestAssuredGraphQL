package com.qa.tests.mutation;

import com.qa.api.RestResource;
import com.qa.graphql_payload.MutationPayload;
import com.qa.graphql_payload.QueryPayload;
import com.qa.tests.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.qa.utils.TestUtils.rawToJSON;

public class CreateCountryTest extends BaseTest {

    @Test(description = "Create Country Test")
    public void createCountry() {
        String countryName = "us15";

        String payload = MutationPayload.createCountry(countryName);
        Response res = RestResource.sendAPIRequest(payload);

        JsonPath js = rawToJSON(res);
        var data = js.get("createCountry");
        System.out.println("GraphQL response: " + data);
    }
}
