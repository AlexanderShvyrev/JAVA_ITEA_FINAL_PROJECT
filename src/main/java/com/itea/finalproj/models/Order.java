package com.itea.finalproj.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private int orderNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "additional_information")
    private String additionalInformation;

    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date orderDate;

    public Order(int orderNumber, Customer customer, String additionalInformation, String status, Date orderDate) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.additionalInformation = additionalInformation;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Order() {

    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDate(Date date) {
    }
}
