package com.AWS.Figma.InventoryManagement.Dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {

    private int Status;
    private Object data;
    private Object error;

    public ApiResponse() {
        Status = HttpStatus.OK.value();
    }

}
