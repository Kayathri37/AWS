package com.AWS.Figma.InventoryManagement.Controller;

import com.AWS.Figma.InventoryManagement.Dto.AddInventoryItemDto;
import com.AWS.Figma.InventoryManagement.Dto.EditInventoryItemDto;
import com.AWS.Figma.InventoryManagement.Dto.QuantityUpdateRequest;
import com.AWS.Figma.InventoryManagement.Entity.InventoryItem;
import com.AWS.Figma.InventoryManagement.Service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService service;

    @PostMapping("/addItem")
    public ResponseEntity<?> addItem(
            @Valid @RequestBody AddInventoryItemDto dto,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or malformed Authorization header");
        }

        InventoryItem savedItem = service.addItem(dto);

        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }


    @PutMapping("/editItem/{id}")
    public ResponseEntity<String> editItem(
            @PathVariable Long id,
            @Valid @RequestBody EditInventoryItemDto dto,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        System.out.println(">> PUT /editItem called for ID: " + id);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or malformed Authorization header");
        }

        String token = authHeader.substring(7);

        boolean isUpdated = service.editItem(id, dto, token);

        if (isUpdated) {
            return ResponseEntity.ok("Item updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
    }
    @GetMapping("/item/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(service.getItemById(id));
    }
    @PutMapping("/addQuantity/{id}")
    public ResponseEntity<String> addQuantity(
            @PathVariable Long id,
            @RequestBody QuantityUpdateRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing or malformed Authorization header");
        }

        String token = authHeader.substring(7);

        boolean updated = service.addQuantity(id, request.getQuantity());

        if (updated) {
            return ResponseEntity.ok("Quantity added and stock status updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.");
        }
    }


}