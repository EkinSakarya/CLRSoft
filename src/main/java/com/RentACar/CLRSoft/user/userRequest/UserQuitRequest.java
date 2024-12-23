package com.RentACar.CLRSoft.user.userRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuitRequest {

    private int id;
    private boolean isActive;
}
