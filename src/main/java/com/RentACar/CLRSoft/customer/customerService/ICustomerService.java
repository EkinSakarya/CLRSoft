package com.RentACar.CLRSoft.customer.customerService;

import com.RentACar.CLRSoft.car.entity.Car;
import com.RentACar.CLRSoft.customer.customerRequest.CustomerUpdateRequest;
import com.RentACar.CLRSoft.customer.customerResponse.FindByIdCustomerResponse;
import com.RentACar.CLRSoft.customer.entity.Customer;
import com.RentACar.CLRSoft.customer.customerRequest.CustomerAddRequest;
import com.RentACar.CLRSoft.customer.customerResponse.CustomerResponse;

import java.util.List;

public interface ICustomerService {

    public Customer update(CustomerUpdateRequest customerUpdateRequest);
    public Customer deleteCustomer(int id);
    List<CustomerResponse> findAll();
    public FindByIdCustomerResponse addCustomer(CustomerAddRequest customerAddRequest);
    public FindByIdCustomerResponse findById(int id);

    void customerHistoryUpdate(Customer customer, Car car);
}
