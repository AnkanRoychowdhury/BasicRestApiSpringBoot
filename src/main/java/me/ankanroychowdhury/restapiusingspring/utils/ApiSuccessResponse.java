package me.ankanroychowdhury.restapiusingspring.utils;

import lombok.Data;

@Data
public class ApiSuccessResponse {
    private final String message;
    private final Integer statusCode;
    private final String statusName;
}
