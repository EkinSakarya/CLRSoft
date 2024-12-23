package com.RentACar.CLRSoft.category.categoryResponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private int id;

    private String name;

    private String description;

    private boolean isActive;
}
