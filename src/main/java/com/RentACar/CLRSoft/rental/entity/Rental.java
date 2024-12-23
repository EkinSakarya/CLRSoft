package com.RentACar.CLRSoft.rental.entity;

import com.RentACar.CLRSoft.car.entity.Car;
import com.RentACar.CLRSoft.customer.entity.Customer;
import com.RentACar.CLRSoft.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Rental")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "CustomerId", referencedColumnName = "Id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CarId", referencedColumnName = "Id")
    private Car car;

    @Column(name = "RentalStartDate")
    @Temporal(TemporalType.DATE)
    private Date rentalStartDate;

    @Column(name = "RentalEndDate")
    @Temporal(TemporalType.DATE)
    private Date rentalEndDate;

    @Column(name = "TotalPrice")
    private double totalPrice;

    public Rental(Customer customer, User user, Car car, Date rentalStartDate, double totalPrice, Date rentalEndDate) {
        this.customer = customer;
        this.user = user;
        this.car = car;
        this.rentalStartDate = rentalStartDate;
        this.totalPrice = totalPrice;
        this.rentalEndDate = rentalEndDate;
    }
}
