package com.RentACar.CLRSoft.user.userRequest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String phoneNumber;


}
