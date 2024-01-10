package com.qa.graphql_payload;

import org.json.JSONObject;

import static com.qa.utils.TestUtils.*;

public class MutationPayload {

    public static String createCountry(String countryName){
        String value = "mutation($country: CountryCreateInput!) {\n" +
                "  createCountry(country: $country) {\n" +
                "    id\n" +
                "    name\n" +
                "    isoCode    \n" +
                "}\n" +
                "}";

//        String variable = "{\n" +
//                "\"country\": {\n" +
//                "  \"name\": \"us11\",\n" +
//                "  \"isoCode\": \"usa\"  \n" +
//                "}\n" +
//                "}";

        JSONObject variable = stringToJSON("{country: {name: "+ countryName +", isoCode: usa}}");

        String payload = graphqlWithVariablesToJsonString(value, variable);
        return payload;
    }
}
