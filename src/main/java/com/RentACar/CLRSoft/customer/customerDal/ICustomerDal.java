package com.RentACar.CLRSoft.customer.customerDal;

import java.util.List;

import com.RentACar.CLRSoft.customer.entity.Customer;


public interface ICustomerDal  {

    List<Customer> findAll();

    void add(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);

    Customer findById(int id);

}
