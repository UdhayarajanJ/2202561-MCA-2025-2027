//package com.fptp.api.exception;
//
//import com.fptp.api.models.ApiResponse;
//import io.swagger.v3.oas.annotations.Hidden;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@Hidden
//@ControllerAdvice
//public class GlobalErrorHandler {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<Object>> HandleGeneralException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new ApiResponse<>(false, "Internal server error", ex.getMessage()));
//    }
//}
