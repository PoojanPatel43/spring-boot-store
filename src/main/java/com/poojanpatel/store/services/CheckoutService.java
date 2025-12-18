package com.poojanpatel.store.services;

import com.poojanpatel.store.dtos.CheckoutRequest;
import com.poojanpatel.store.dtos.CheckoutResponse;
import com.poojanpatel.store.entities.Order;
import com.poojanpatel.store.exceptions.CartEmptyException;
import com.poojanpatel.store.exceptions.CartNotFoundException;
import com.poojanpatel.store.repositories.CartRepository;
import com.poojanpatel.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CheckoutService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final CartService cartService;

    public CheckoutResponse checkout(CheckoutRequest checkoutRequest) {
        var cart = cartRepository.getCartWithItems(checkoutRequest.getCartId()).orElse(null);
        if (cart == null) {
           throw new CartNotFoundException();
        }

        if(cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        cartService.emptyCart(cart.getId());


        return new CheckoutResponse(order.getId());
    }
}
