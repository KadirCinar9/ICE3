package ca.gbc.inventoryservice.service;

import ca.gbc.inventoryservice.dto.InventoryDTO;
import ca.gbc.inventoryservice.model.Inventory;
import ca.gbc.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }

    @Override
    public Inventory addInventory(InventoryDTO inventoryDTO) {
        // Convert DTO to Entity
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryDTO.getSkuCode());
        inventory.setQuantity(inventoryDTO.getQuantity());
        return inventoryRepository.save(inventory);
    }

    @Override
    public boolean updateInventory(String skuCode, Integer quantity) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode);
        if (inventory != null) {
            inventory.setQuantity(inventory.getQuantity() + quantity);
            inventoryRepository.save(inventory);
            return true;
        }
        return false;
    }
}
