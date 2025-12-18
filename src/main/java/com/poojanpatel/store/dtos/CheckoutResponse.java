package com.poojanpatel.store.dtos;

import lombok.Data;

@Data
public class CheckoutResponse {

    private Long orderID;

    public CheckoutResponse(Long orderID) {
        this.orderID = orderID;
    }
}
