package com.RentACar.CLRSoft.rental.rentalResponse;

import com.RentACar.CLRSoft.car.entity.Car;
import com.RentACar.CLRSoft.customer.entity.Customer;
import com.RentACar.CLRSoft.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponse {

    private Customer customer;

    private User user;

    private Car car;

    private Date rentalStartDate;

    private Date rentalEndDate;
}
