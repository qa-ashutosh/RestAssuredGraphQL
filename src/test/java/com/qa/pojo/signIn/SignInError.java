package com.qa.pojo.signIn;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignInError {

    @JsonProperty("userMessage")
    private Object userMessage;
    @JsonProperty("errorCode")
    private Integer errorCode;
    @JsonProperty("devMessage")
    private String devMessage;
    @JsonProperty("path")
    private Object path;
    @JsonProperty("rowNumber")
    private Integer rowNumber;
    @JsonProperty("value")
    private Object value;

    @JsonProperty("userMessage")
    public Object getUserMessage() {
        return userMessage;
    }

    @JsonProperty("userMessage")
    public void setUserMessage(Object userMessage) {
        this.userMessage = userMessage;
    }

    @JsonProperty("errorCode")
    public Integer getErrorCode() {
        return errorCode;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("devMessage")
    public String getDevMessage() {
        return devMessage;
    }

    @JsonProperty("devMessage")
    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    @JsonProperty("path")
    public Object getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(Object path) {
        this.path = path;
    }

    @JsonProperty("rowNumber")
    public Integer getRowNumber() {
        return rowNumber;
    }

    @JsonProperty("rowNumber")
    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    @JsonProperty("value")
    public Object getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Object value) {
        this.value = value;
    }

}