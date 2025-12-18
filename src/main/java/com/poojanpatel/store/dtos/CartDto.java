package com.poojanpatel.store.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class CartDto {
    private UUID id;
    public List<CartItemDto> items = new ArrayList<>();
    public BigDecimal totalPrice = BigDecimal.ZERO;

}
