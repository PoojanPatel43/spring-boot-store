package com.poojanpatel.store.mappers;

import com.poojanpatel.store.dtos.CartDto;
import com.poojanpatel.store.dtos.CartItemDto;
import com.poojanpatel.store.entities.Cart;
import com.poojanpatel.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "items", source = "cartItems")
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
