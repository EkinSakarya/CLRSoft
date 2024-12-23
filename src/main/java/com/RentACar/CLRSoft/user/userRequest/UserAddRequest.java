package com.RentACar.CLRSoft.user.userRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddRequest {

    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private boolean isActive;

    private String phoneNumber;
}
