package com.RentACar.CLRSoft.customer.customerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {

    private int id;

    private String name;

    private String surname;

    private String password;

    private String email;

    private String phoneNumber;

    private String address;



}
