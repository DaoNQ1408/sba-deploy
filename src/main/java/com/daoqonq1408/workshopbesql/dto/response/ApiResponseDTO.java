package com.daoqonq1408.workshopbesql.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO<T> {
    private String errorCode;
    private String message;
    private T data;

    public static <T> ApiResponseDTO<T> success(String statuscode, String message, T data) {
        return new ApiResponseDTO<>(statuscode, message, data);
    }

    public static <T> ApiResponseDTO<List<T>> success(String statuscode, String message, List<T> data) {
        return new ApiResponseDTO<>(statuscode, message, data);
    }
}
