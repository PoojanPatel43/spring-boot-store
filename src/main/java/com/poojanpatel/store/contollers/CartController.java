package com.poojanpatel.store.contollers;

import com.poojanpatel.store.dtos.*;
import com.poojanpatel.store.dtos.AddItemToCartRequest;
import com.poojanpatel.store.dtos.CartDto;
import com.poojanpatel.store.dtos.CartItemDto;
import com.poojanpatel.store.dtos.UpdateCartItemRequest;
import com.poojanpatel.store.exceptions.CartNotFoundException;
import com.poojanpatel.store.exceptions.ProductNotFoundException;
import com.poojanpatel.store.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
@Tag(name = "Carts")
public class CartController {

    private final CartService cartService;


    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {

        var cartDto = cartService.createCart();

        var uri = uriBuilder.path("/cart/{id}").buildAndExpand(cartDto.getId()).toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("{cartId}/items")
    @Operation(summary = "Add item to cart")
    public ResponseEntity<CartItemDto> addItemToCart(
            @Parameter(description = "Cart ID")
            @PathVariable UUID cartId, @RequestBody AddItemToCartRequest request)
    {

        var cartItemDto = cartService.addToCart(cartId, request.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId){
        var cart = cartService.getCart(cartId);

        return ResponseEntity.ok(cart);

    }

    @PutMapping("{cartId}/items/{productId}")
    public ResponseEntity<?> updateItem(@PathVariable("cartId") UUID cartId,
                                        @PathVariable("productId") Long  productId,
                                        @Valid @RequestBody UpdateCartItemRequest request){

        var cartItem = cartService.updateItem(cartId, productId, request.getQuantity());
        return ResponseEntity.ok(cartItem);

    }

    @DeleteMapping("{cartId}/items/{productId}")
    public ResponseEntity<?> removeItem(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("productId") Long productId
    ){
        cartService.removeItem(cartId, productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("{cartId}/items")
    public ResponseEntity<?> emptyCart(@PathVariable("cartId") UUID cartId){
        cartService.emptyCart(cartId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<?> CartNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("Error", "Cart not found.")
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> productNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("Error", "Product not found in the cart.")
        );
    }
}
