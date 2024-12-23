package com.RentACar.CLRSoft.category.categoryRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryActiveRequest {

    private  int id;
    private boolean isActive;
}
