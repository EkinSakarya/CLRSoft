package com.RentACar.CLRSoft.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "IsActive")
    private boolean isActive;

    @Column(name = "PhoneNumber")
    private String phoneNumber;



    public User(String name, String surname, String email, String password, boolean isActive,String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.phoneNumber=phoneNumber;
    }
}
