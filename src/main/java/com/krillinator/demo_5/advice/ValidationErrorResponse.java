package com.krillinator.demo_5.advice;

import java.util.List;

public record ValidationErrorResponse(
        int status,
        String message,
        List<ApiErrorResponseDTO> errors
) {
}
