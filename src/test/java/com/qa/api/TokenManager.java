package com.qa.api;

import com.qa.utils.ConfigLoader;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

public class TokenManager {
    private static String accessToken; // This static instance variable will be shared for multi threads to support parallel execution, until we overwrite the variable (which we are not)
    private static Instant expiryTime; // This static instance variable will be shared for multi threads to support parallel execution, until we overwrite the variable (which we are not)
    private static String graghQLToken; // This static instance variable will be shared for multi threads to support parallel execution, until we overwrite the variable (which we are not)
    private static Instant graghQLTokenExpiryTime; // This static instance variable will be shared for multi threads to support parallel execution, until we overwrite the variable (which we are not)

    public synchronized static String getToken(){  // The 'synchronized' keyword prevents the race-conditions, when two threads trying to access this method at the same time, the another thread will wait in this case.
        try{
            if(accessToken == null || Instant.now().isAfter(expiryTime)){
                System.out.println("Renewing token...");
                Response response = renewToken();
                accessToken = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryDurationInSeconds - 300); // Taking a buffer of 5 mins before token expiry
            } else {
                System.out.println("Token is good to use");
            }
        }
        catch (Exception e){
            throw new RuntimeException("ABORT!! Failed to get token.");
        }
        return accessToken;
    }

    private static Response renewToken(){
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        Response response = RestResource.postAccount(formParams);

        if(response.statusCode() != 200) {
            throw new RuntimeException("ABORT!! Renew Token failed");
        }
        return response;
    }

    public synchronized static String getGraphQLToken(){  // The 'synchronized' keyword prevents the race-conditions, when two threads trying to access this method at the same time, the another thread will wait in this case.
        try{
            if(graghQLToken == null || Instant.now().isAfter(graghQLTokenExpiryTime)){
                System.out.println("Renewing token...");
                Response response = renewGraphQLToken();
                graghQLToken = response.path("data.accessToken");
                int expiryDurationInSeconds = response.path("data.expiresIn");
                graghQLTokenExpiryTime = Instant.now().plusSeconds(expiryDurationInSeconds - 300); // Taking a buffer of 5 mins before token expiry
            } else {
                System.out.println("Token is good to use");
            }
        }
        catch (Exception e){
            throw new RuntimeException("ABORT!! Failed to get token.");
        }
        return graghQLToken;
    }

    private static Response renewGraphQLToken(){
        String paylod = "{\n" +
                "  \"userName\": \"ashutosh\",\n" +
                "  \"password\": \"Test@1234\",\n" +
                "  \"rememberMe\": true\n" +
                "}";
        Response response = RestResource.postUserAccount(paylod);

        if(response.statusCode() != 200) {
            throw new RuntimeException("ABORT!! Renew Token for GraphQL failed");
        }
        return response;
    }
}
