package com.RentACar.CLRSoft.category.restApi;

import com.RentACar.CLRSoft.category.categoryRequest.CategoryActiveRequest;
import com.RentACar.CLRSoft.category.categoryRequest.CategoryAddRequest;
import com.RentACar.CLRSoft.category.categoryRequest.CategoryUpdateRequest;
import com.RentACar.CLRSoft.category.categoryResponse.CategoryResponse;
import com.RentACar.CLRSoft.category.categoryResponse.FindByIdCategoryResponse;
import com.RentACar.CLRSoft.category.categoryService.ICategoryService;
import com.RentACar.CLRSoft.category.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @PostMapping("/addcategory")
    public FindByIdCategoryResponse addCustomer(@RequestBody CategoryAddRequest categoryAddRequest) {

        return categoryService.addCategory(categoryAddRequest);
    }

    @GetMapping("/getcategorybyid/{id}")
    public FindByIdCategoryResponse findById(@PathVariable int id) {
        return categoryService.findById(id) ;
    }
    @PutMapping("/categoryupdate/{id}")
    public Category categoryUpdate(@RequestBody CategoryUpdateRequest categoryUpdateRequest, @PathVariable int id) {
        categoryUpdateRequest.setId(id);
        return categoryService.update(categoryUpdateRequest);
    }
    @PutMapping("/categoryupdateactivity/{id}")
    public Category categoryUpdate(@RequestBody CategoryActiveRequest categoryActiveRequest, @PathVariable int id) {
        categoryActiveRequest.setId(id);
        return categoryService.updateActive(categoryActiveRequest);
    }


}
