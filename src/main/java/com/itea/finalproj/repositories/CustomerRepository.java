package com.itea.finalproj.repositories;

import com.itea.finalproj.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByFullName(String fullName);
}
