package com.qa.pojo.signIn;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignInResponse {

    @JsonProperty("apiResponseStatus")
    private String apiResponseStatus;
    @JsonProperty("httpStatus")
    private Integer httpStatus;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private SignInResponseData data;
    @JsonProperty("errors")
    private List<Object> errors;

    @JsonProperty("apiResponseStatus")
    public String getApiResponseStatus() {
        return apiResponseStatus;
    }

    @JsonProperty("apiResponseStatus")
    public void setApiResponseStatus(String apiResponseStatus) {
        this.apiResponseStatus = apiResponseStatus;
    }

    @JsonProperty("httpStatus")
    public Integer getHttpStatus() {
        return httpStatus;
    }

    @JsonProperty("httpStatus")
    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("data")
    public SignInResponseData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(SignInResponseData data) {
        this.data = data;
    }

    @JsonProperty("errors")
    public List<Object> getErrors() {
        return errors;
    }

    @JsonProperty("errors")
    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

}
