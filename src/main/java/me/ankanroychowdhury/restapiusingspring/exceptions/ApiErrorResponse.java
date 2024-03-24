package me.ankanroychowdhury.restapiusingspring.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiErrorResponse {
    private final String message;
    private final Integer statusCode;
    private final String statusName;
    private final String path;
}
