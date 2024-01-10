package com.qa.pojo.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignInRequest {

    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("rememberMe")
    private Boolean rememberMe;

    @JsonProperty("userName")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("userName")
    public SignInRequest setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public SignInRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    @JsonProperty("rememberMe")
    public Boolean getRememberMe() {
        return rememberMe;
    }

    @JsonProperty("rememberMe")
    public SignInRequest setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
        return this;
    }

}