package com.microserivce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microserivce.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
