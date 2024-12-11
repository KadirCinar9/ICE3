package ca.gbc.inventoryservice.controller;

import ca.gbc.inventoryservice.dto.InventoryDTO;
import ca.gbc.inventoryservice.model.Inventory;
import ca.gbc.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // Check if the product is in stock
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }


    // Add new inventory item
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InventoryDTO> addInventory(@RequestBody InventoryDTO inventoryDTO) {
        Inventory inventory = inventoryService.addInventory(inventoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new InventoryDTO(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity()));
    }

    // Update existing inventory item
    @PutMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateInventory(@PathVariable String skuCode, @RequestParam Integer quantity) {
        boolean updated = inventoryService.updateInventory(skuCode, quantity);
        if (updated) {
            return ResponseEntity.ok("Inventory updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

    }
}
