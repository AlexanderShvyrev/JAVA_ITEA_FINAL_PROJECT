package com.itea.finalproj.services;

import com.itea.finalproj.models.Customer;
import com.itea.finalproj.models.Order;
import com.itea.finalproj.repositories.CustomerRepository;
import com.itea.finalproj.repositories.OrderRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    protected final OrderRepository orderRepository;
    protected final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int orderNumber) {
        try {
            return orderRepository.findById(orderNumber)
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Order createOrder(Order order) {

        order.setOrderDate(new Date());

        return orderRepository.save(order);
    }
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public void removeOrderFromCustomer(int orderNumber) {
        Order order = orderRepository.findById(orderNumber)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        Customer customer = order.getCustomer();
        customer.getOrders().remove(order);
        customerRepository.save(customer);
    }
}
