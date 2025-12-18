package com.poojanpatel.store.contollers;

import com.poojanpatel.store.dtos.CheckoutRequest;
import com.poojanpatel.store.dtos.CheckoutResponse;
import com.poojanpatel.store.dtos.ErrorDto;
import com.poojanpatel.store.exceptions.CartEmptyException;
import com.poojanpatel.store.exceptions.CartNotFoundException;
import com.poojanpatel.store.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(
            @Valid @RequestBody CheckoutRequest checkoutRequest) {

        return checkoutService.checkout(checkoutRequest);
    }

    @ExceptionHandler({CartEmptyException.class, CartNotFoundException.class})
    public ResponseEntity<ErrorDto> handleError(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorDto(e.getMessage()));

    }

}
