package ca.gbc.inventoryservice.service;

import ca.gbc.inventoryservice.dto.InventoryDTO;
import ca.gbc.inventoryservice.model.Inventory;

public interface InventoryService {

    boolean isInStock(String skuCode, Integer quantity);

    Inventory addInventory(InventoryDTO inventoryDTO);

    boolean updateInventory(String skuCode, Integer quantity);
}
