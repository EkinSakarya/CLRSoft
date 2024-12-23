package com.RentACar.CLRSoft.login.loginService;

import com.RentACar.CLRSoft.ClrSoftApplication;
import com.RentACar.CLRSoft.customer.customerResponse.CustomerResponse;
import com.RentACar.CLRSoft.customer.customerService.ICustomerService;
import com.RentACar.CLRSoft.login.loginRequest.LoginRequest;
import com.RentACar.CLRSoft.user.userResponse.UserResponse;
import com.RentACar.CLRSoft.user.userService.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginManager implements ILoginService{

    private final ICustomerService customerService;
    private final IUserService userService;
    private final ModelMapper modelMapper;

    public LoginManager(ICustomerService customerService,IUserService userService,ModelMapper modelMapper)
    {
        this.customerService=customerService;
        this.userService=userService;
        this.modelMapper=modelMapper;
    }

    @Override
    public String login(LoginRequest loginRequest) {

        List<UserResponse> users= userService.findAll();
        List<CustomerResponse> customers=customerService.findAll();
        String welcome="";

        for(UserResponse user :users)
        {
            if(user.getEmail().equalsIgnoreCase(loginRequest.getEmail())&&user.getPassword().equals(loginRequest.getPassword()))
            {
                ClrSoftApplication.userPermission=1;
                welcome="Hoşgeldiniz "+user.getName()+" "+user.getSurname();
                ClrSoftApplication.userOrCustomerId=user.getId();
            }
        }
        for(CustomerResponse customer:customers)
        {

            if(customer.getEmail().equalsIgnoreCase(loginRequest.getEmail())&&customer.getPassword().equals(loginRequest.getPassword()))
            {
                ClrSoftApplication.userPermission=0;
                welcome="Hoşgeldiniz "+customer.getName()+" "+customer.getSurname();
                ClrSoftApplication.userOrCustomerId=customer.getId();
            }
        }
        if(welcome=="")
        {
            welcome="Şifre ya da email yanlış.";
        }
        return welcome;
    }
}
