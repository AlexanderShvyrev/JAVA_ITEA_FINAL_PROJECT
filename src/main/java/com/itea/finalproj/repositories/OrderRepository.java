package com.itea.finalproj.repositories;

import com.itea.finalproj.models.Customer;
import com.itea.finalproj.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);
    List<Order> findByStatus(String status);
}
