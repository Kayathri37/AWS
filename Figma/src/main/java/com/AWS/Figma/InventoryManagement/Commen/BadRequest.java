package com.AWS.Figma.InventoryManagement.Commen;


import lombok.Data;

import java.util.List;

@Data
public class BadRequest extends RuntimeException{
    List<error>errors;


    public BadRequest(String message, List<error> check) {
        super(message);
        errors=check;
    }
}
