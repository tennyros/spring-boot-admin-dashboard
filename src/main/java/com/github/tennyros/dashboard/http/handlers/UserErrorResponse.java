package com.github.tennyros.dashboard.http.handlers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserErrorResponse {

    private Map<String, String> fieldErrors;
    private String error;
    private LocalDateTime timestamp;

    public UserErrorResponse(Map<String, String> fieldErrors, LocalDateTime timestamp) {
        this.fieldErrors = fieldErrors;
        this.timestamp = timestamp;
    }

    public UserErrorResponse(String error, LocalDateTime timestamp) {
        this.error = error;
        this.timestamp = timestamp;
    }
}
