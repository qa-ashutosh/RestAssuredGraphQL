package com.qa.graphql_payload;

import org.json.JSONObject;

import static com.qa.utils.TestUtils.*;

public class QueryPayload {
    public static String getCountry(){
        String value = "query countries\n" +
                "{\n" +
                "  countries\n" +
                "  {\n" +
                "    id\n" +
                "    name\n" +
                "    isoCode    \n" +
                "  }\n" +
                "}";

//        String variables = "{}";

        String payload = graphqlToJsonString(value);
//        String payload = graphqlWithVariablesToJsonString(value, variables);
        System.out.println("Final Payload: "+ payload);
        return payload;
    }

    public static String getCountryById(String countryId){
        String value = "query country($countryId : ID!)\n" +
                "{\n" +
                "  country(countryId : $countryId)\n" +
                "  {\n" +
                "    name\n" +
                "    isoCode    \n" +
                "  }\n" +
                "}";

        JSONObject variables = stringToJSON("{countryId: "+ countryId +"}");

//        String payload = graphqlToJsonString(value);
        String payload = graphqlWithVariablesToJsonString(value, variables);
        System.out.println("Final Payload: "+ payload);
        return payload;
    }

    public static String getCharacterById(){
        String value = "query{\n" +
                "  character(characterId: 4947){\n" +
                "    id\n" +
                "    name\n" +
                "    status\n" +
                "    type\n" +
                "    gender\n" +
                "  }\n" +
                "}";

        String payload = graphqlToJsonString(value);
        return payload;
    }

//    public static String getCharacters(){
//        String value = "query($name: String!){\n" +
//                "  characters(filters: {name: $name}){\n" +
//                "    result{ \n" +
//                "      name\n" +
//                "\t\t\tgender\n" +
//                "    }\n" +
//                "  }\n" +
//                "}";
//
//        String variables = "{\n" +
//                "  \"name\" : \"Tom\"\n" +
//                "}";
//
//        String payload = graphqlWithVariablesToJsonString(value, variables);
//        return payload;
//    }
}
