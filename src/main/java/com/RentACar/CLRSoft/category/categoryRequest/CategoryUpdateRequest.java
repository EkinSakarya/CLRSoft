package com.RentACar.CLRSoft.category.categoryRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest {

    private  int id;

    private String name;

    private String description;
}
