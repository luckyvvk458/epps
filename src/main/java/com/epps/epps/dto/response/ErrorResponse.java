package com.epps.epps.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {

    private OffsetDateTime timestamp;

    private int status;

    private String error;

    private String message;

    private Map<String, String> fieldErrors;
}