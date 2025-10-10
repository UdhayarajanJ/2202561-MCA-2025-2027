package com.fptp.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private boolean success;
    private String message;
    private Map<String, String> errors;
}
