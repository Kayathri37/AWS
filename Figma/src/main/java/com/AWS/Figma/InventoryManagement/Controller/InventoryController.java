package com.AWS.Figma.InventoryManagement.Controller;

import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import com.AWS.Figma.InventoryManagement.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*")
public class InventoryController {
    @Autowired
    private InventoryService service;

    @PostMapping("/addItem")
    public ResponseEntity<?> addItem(
            @RequestBody InventoryItem item,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // Check for Authorization header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or malformed Authorization header");
        }

        // Extract token
        String token = authHeader.substring(7);

        // Optionally: You can validate the token or pass it to service
        InventoryItem savedItem = service.addItem(item); // If needed, also pass token

        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }
}