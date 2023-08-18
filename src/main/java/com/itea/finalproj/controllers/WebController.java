package com.itea.finalproj.controllers;

import com.itea.finalproj.models.Customer;
import com.itea.finalproj.models.CustomerForm;
import com.itea.finalproj.models.Order;
import com.itea.finalproj.models.OrderForm;
import com.itea.finalproj.services.CustomerService;
import com.itea.finalproj.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class WebController {

    private final OrderService orderService;
    private final CustomerService customerService;

    public WebController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @GetMapping("/listOrders")
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "list_orders";
    }

    @GetMapping("/viewOrder/{orderNumber}")
    public String viewOrder(@PathVariable int orderNumber, Model model) {
        Order order = orderService.getOrderById(orderNumber);
        model.addAttribute("order", order);
        return "view_order";
    }

    @GetMapping("/viewCustomer/{customerId}")
    public String viewCustomer(@PathVariable int customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "view_customer";
    }

    @GetMapping("/createOrder")
    public String createOrderForm(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("orderForm", new OrderForm());
        return "order_form";
    }

    @PostMapping("/createOrder")
    public String createOrderSubmit(@ModelAttribute OrderForm orderForm, Model model) {
        if (orderForm.getCustomerId() == null) {
            // Handle the case where customer ID is not provided
            // You might display an error message or perform other actions
        } else {
            // Fetch the selected customer
            Customer customer = customerService.getCustomerById(orderForm.getCustomerId());

            // Create a new Order instance and set its attributes
            Order newOrder = new Order();
            newOrder.setCustomer(customer);
            newOrder.setAdditionalInformation(orderForm.getAdditionalInformation());
            newOrder.setStatus("Waiting");
            newOrder.setDate(new Date());

            // Save the new order
            orderService.createOrder(newOrder);
        }

        return "redirect:/listOrders"; // Redirect to the list of orders
    }

    @GetMapping("/addCustomer")
    public String addCustomerForm(Model model) {
        model.addAttribute("customerForm", new CustomerForm());
        return "customer_form";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute Customer customer, @RequestParam(required = false) String redirect) {
        customerService.createCustomer(customer);
        if (redirect != null) {
            return "redirect:/" + redirect;
        }
        return "redirect:/createOrder";
    }

    @GetMapping("/editOrderStatus/{orderNumber}")
    public String editOrderStatusForm(@PathVariable int orderNumber, Model model) {
        Order order = orderService.getOrderById(orderNumber);
        model.addAttribute("order", order);
        return "edit_order_status";
    }

    @PostMapping("/editOrderStatus/{orderNumber}")
    public String editOrderStatus(@PathVariable int orderNumber, @ModelAttribute Order order) {
        Order existingOrder = orderService.getOrderById(orderNumber);

        existingOrder.setStatus(order.getStatus());
        existingOrder.setOrderDate(new Date());

        orderService.updateOrder(existingOrder);

        return "redirect:/viewOrder/" + orderNumber;
    }

    @PostMapping("/removeOrder")
    public String removeOrderFromCustomer(@RequestParam int orderNumber) {
        orderService.removeOrderFromCustomer(orderNumber);
        return "redirect:/listOrders";
    }
}