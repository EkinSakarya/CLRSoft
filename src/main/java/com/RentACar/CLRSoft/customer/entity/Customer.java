package com.RentACar.CLRSoft.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Customer") //
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id") //
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "RentalStatus")
    private boolean rentalStatus;

    @Column(name = "Address")
    private String address;

    @Column(name = "RegistrationDate")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Column(name = "RentalHistory")
    private String rentalHistory;

    public Customer(String name, String surname, String password, String email, String phoneNumber, boolean rentalStatus, String address, Date registrationDate, String rentalHistory) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.rentalStatus = rentalStatus;
        this.address = address;
        this.registrationDate = registrationDate;
        this.rentalHistory = rentalHistory;
    }
}
