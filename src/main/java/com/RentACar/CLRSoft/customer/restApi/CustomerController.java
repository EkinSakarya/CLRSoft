package com.RentACar.CLRSoft.customer.restApi;

import com.RentACar.CLRSoft.ClrSoftApplication;
import com.RentACar.CLRSoft.customer.customerRequest.CustomerAddRequest;
import com.RentACar.CLRSoft.customer.customerRequest.CustomerUpdateRequest;
import com.RentACar.CLRSoft.customer.customerResponse.CustomerResponse;
import com.RentACar.CLRSoft.customer.customerResponse.FindByIdCustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.RentACar.CLRSoft.customer.entity.Customer;
import com.RentACar.CLRSoft.customer.customerService.ICustomerService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;


    @GetMapping("/getcustomers")
    public List<CustomerResponse> getCustomers() {
        return customerService.findAll();
    }

    @PostMapping("/addcustomer")
    public FindByIdCustomerResponse addCustomer(@RequestBody CustomerAddRequest customerAddRequest) {
        return customerService.addCustomer(customerAddRequest);
    }
    @GetMapping("/getcustomerbyid/{id}")
    public FindByIdCustomerResponse findById(@PathVariable int id) {
        return customerService.findById(id) ;
    }
    @GetMapping("/deletecustomerbyid/{id}")
    public Customer deleteCustomer(@PathVariable int id)
    {
        return customerService.deleteCustomer(id);
    }
    @PutMapping("/updatecustomer/{id}")
    public Customer updateCustomer(@RequestBody CustomerUpdateRequest customerUpdateRequest ,@PathVariable int id)
    {
        customerUpdateRequest.setId(id);
        return customerService.update(customerUpdateRequest);
    }
    @PutMapping("/updatecustomer")
    public Customer updateCustomerForCustomer(@RequestBody CustomerUpdateRequest customerUpdateRequest)
    {
        customerUpdateRequest.setId(ClrSoftApplication.userOrCustomerId);
        return customerService.update(customerUpdateRequest);
    }
}
