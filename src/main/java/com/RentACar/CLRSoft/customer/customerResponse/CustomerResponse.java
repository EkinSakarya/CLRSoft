package com.RentACar.CLRSoft.customer.customerResponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private int id;

    private String name;

    private String surname;

    private String password;

    private String email;

    private String phoneNumber;

    private boolean rentalStatus;

    private String address;

    private Date registrationDate;

    private String rentalHistory;

}
