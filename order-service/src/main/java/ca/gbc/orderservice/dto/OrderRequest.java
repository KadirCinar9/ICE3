package ca.gbc.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderRequest(
        String skuCode,
        BigDecimal price,
        Integer quantity
) {}
