package com.project.springdemo.service;

import com.project.springdemo.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);

    List<Customer> searchCustomer(String searchName);
}
