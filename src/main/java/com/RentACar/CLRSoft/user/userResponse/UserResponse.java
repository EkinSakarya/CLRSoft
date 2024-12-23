package com.RentACar.CLRSoft.user.userResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private int id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private boolean isActive;

    private String phoneNumber;
}
