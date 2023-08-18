package com.itea.finalproj.services;

import com.itea.finalproj.models.Customer;
import com.itea.finalproj.repositories.CustomerRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(int customerId) {
        try {
            return customerRepository.findById(customerId)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
