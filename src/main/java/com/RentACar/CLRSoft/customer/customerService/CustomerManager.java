package com.RentACar.CLRSoft.customer.customerService;

import com.RentACar.CLRSoft.Validator;
import com.RentACar.CLRSoft.car.entity.Car;
import com.RentACar.CLRSoft.customer.customerDal.ICustomerDal;
import com.RentACar.CLRSoft.customer.customerRequest.CustomerUpdateRequest;
import com.RentACar.CLRSoft.customer.customerResponse.FindByIdCustomerResponse;
import com.RentACar.CLRSoft.customer.entity.Customer;
import com.RentACar.CLRSoft.customer.customerRequest.CustomerAddRequest;
import com.RentACar.CLRSoft.customer.customerResponse.CustomerResponse;
import com.RentACar.CLRSoft.user.userDal.IUserDal;
import com.RentACar.CLRSoft.user.userService.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements ICustomerService {

    private final ICustomerDal customerDal;
    private final IUserDal userDal;
    private final ModelMapper modelMapper;
    private final Validator validator=new Validator();


    @Autowired
    public CustomerManager(ICustomerDal customerDal, ModelMapper modelMapper,IUserDal userDal) {
        this.customerDal = customerDal;
        this.modelMapper = modelMapper;
        this.userDal=userDal;
    }


    @Override
    public Customer update(CustomerUpdateRequest customerUpdateRequest) {

        Customer oldCustomer =  customerDal.findById(customerUpdateRequest.getId());
        if (oldCustomer == null) {
            return null;
        }
        if(!validator.isNumber(customerUpdateRequest.getPhoneNumber())||!validator.isMail(customerUpdateRequest.getEmail()))
        {
            return null;
        }
        if(validator.isCustomerEmailValid(customerDal.findAll(),customerUpdateRequest.getEmail(),customerUpdateRequest.getId())||validator.isCustomerPhoneValid(customerDal.findAll(),customerUpdateRequest.getPhoneNumber(),customerUpdateRequest.getId()))
        {
            return null;
        }


        Customer updatedcustomer =modelMapper.map(customerUpdateRequest,Customer.class);

        updatedcustomer.setRegistrationDate(oldCustomer.getRegistrationDate());
        updatedcustomer.setRentalHistory(oldCustomer.getRentalHistory());
        updatedcustomer.setRentalStatus(oldCustomer.isRentalStatus());

        customerDal.update(updatedcustomer);

        return updatedcustomer;
    }

    @Override
    public Customer deleteCustomer(int id) {
        Customer customer = customerDal.findById(id);
        if (customer == null) {
            return null;
        }
        customerDal.delete(customer);
        return customer;
    }

    @Override
    public List<CustomerResponse> findAll() {

        List<Customer> customers = customerDal.findAll();

        return customers.stream().map(
                        customer -> modelMapper.map(customer, CustomerResponse.class)).
                collect(Collectors.toList());
    }

    @Override
    public FindByIdCustomerResponse addCustomer(CustomerAddRequest customerAddRequest) {

        Customer customer= modelMapper.map(customerAddRequest,Customer.class);
        List<Customer> customers=customerDal.findAll();

        if(!validator.isNumber(customerAddRequest.getPhoneNumber())||!validator.isMail(customerAddRequest.getEmail()))
        {
            return null;
        }
        if(validator.isCustomerEmailValid(customerDal.findAll(),customerAddRequest.getEmail())||validator.isCustomerPhoneValid(customerDal.findAll(),customerAddRequest.getPhoneNumber()))
        {
            return null;
        }

        if(validator.isUserEmailValid(userDal.findAll(),customerAddRequest.getEmail())||validator.isUserPhoneValid(userDal.findAll(),customerAddRequest.getPhoneNumber()))
        {
            return null;
        }

        String email= customerAddRequest.getEmail();

        for(Customer customersAll :customers)
        {
            System.out.println(customersAll.getEmail());
            if(customersAll.getEmail().equals(email))
            {
                return null;
            }
        }
        customer.setRegistrationDate(new Date());
        this.customerDal.add(customer);
        return this.findById(customer.getId());
    }

    @Override
    public FindByIdCustomerResponse findById(int id) {
        Customer customer = customerDal.findById(id);
        if (customer == null) {
            return null;
        }
        return modelMapper.map(customer,FindByIdCustomerResponse.class);
    }

    @Override
    public void customerHistoryUpdate(Customer customer, Car car)
    {
        String customerHistory =customer.getRentalHistory();

        if(customerHistory == "null") {
            customer.setRentalHistory(customer.getRentalHistory() + "Daha önce " + car.getBrand() + " Markalı bir araç kiraladı.---\n");
        }
        else
        {
            customer.setRentalHistory("Daha önce " + car.getBrand() + " Markalı bir araç kiraladı.---\n");
        }
    }

}
