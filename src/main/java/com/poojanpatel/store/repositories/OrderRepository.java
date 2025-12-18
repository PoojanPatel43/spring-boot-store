package com.poojanpatel.store.repositories;

import com.poojanpatel.store.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}