package io.toughbox.auth.exception.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {
    private final String errorCode;
    private final String errorText;
}

