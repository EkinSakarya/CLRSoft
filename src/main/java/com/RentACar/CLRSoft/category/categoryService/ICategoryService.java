package com.RentACar.CLRSoft.category.categoryService;

import com.RentACar.CLRSoft.category.categoryRequest.CategoryActiveRequest;
import com.RentACar.CLRSoft.category.categoryRequest.CategoryAddRequest;
import com.RentACar.CLRSoft.category.categoryRequest.CategoryUpdateRequest;
import com.RentACar.CLRSoft.category.categoryResponse.CategoryResponse;
import com.RentACar.CLRSoft.category.categoryResponse.FindByIdCategoryResponse;
import com.RentACar.CLRSoft.category.entity.Category;


import java.util.List;

public interface ICategoryService {
    public List<CategoryResponse> findAll();
    public FindByIdCategoryResponse addCategory(CategoryAddRequest categoryAddRequest);
    public Category update(CategoryUpdateRequest categoryUpdateRequest);
    public Category deleteCategory(int id);
    public FindByIdCategoryResponse findById(int id);
    public Category updateActive(CategoryActiveRequest categoryActiveRequest);
}
