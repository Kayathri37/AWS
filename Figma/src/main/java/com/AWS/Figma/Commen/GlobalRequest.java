package com.AWS.Figma.Commen;


import com.AWS.Figma.DTO.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalRequest {

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ApiResponse>handleBadRequest(BadRequest badRequest){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setError(badRequest.getErrors());
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status (apiResponse.getStatus()).body(apiResponse);
    }
}
