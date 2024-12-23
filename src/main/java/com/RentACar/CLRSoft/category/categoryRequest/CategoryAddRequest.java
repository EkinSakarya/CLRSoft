package com.RentACar.CLRSoft.category.categoryRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAddRequest {

    private String name;

    private String description;

    private boolean isActive;
}
