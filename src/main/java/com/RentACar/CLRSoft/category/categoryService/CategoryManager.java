package com.RentACar.CLRSoft.category.categoryService;

import com.RentACar.CLRSoft.category.categoryDal.ICategoryDal;
import com.RentACar.CLRSoft.category.categoryRequest.CategoryActiveRequest;
import com.RentACar.CLRSoft.category.categoryRequest.CategoryAddRequest;
import com.RentACar.CLRSoft.category.categoryRequest.CategoryUpdateRequest;
import com.RentACar.CLRSoft.category.categoryResponse.CategoryResponse;
import com.RentACar.CLRSoft.category.categoryResponse.FindByIdCategoryResponse;
import com.RentACar.CLRSoft.category.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements ICategoryService{

    private ICategoryDal categoryDal;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryManager(ICategoryDal categoryDal, ModelMapper modelMapper) {
        this.categoryDal = categoryDal;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryDal.findAll();

        return categories.stream().map(
                        category -> modelMapper.map(category, CategoryResponse.class)).
                collect(Collectors.toList());
    }

    @Override
    public FindByIdCategoryResponse addCategory(CategoryAddRequest categoryAddRequest) {

        Category newCategory = modelMapper.map(categoryAddRequest,Category.class);
        newCategory.setActive(true);

        this.categoryDal.add(newCategory);

        return this.findById(newCategory.getId());
    }

    @Override
    public Category update(CategoryUpdateRequest categoryUpdateRequest) {
        Category oldCategory =  categoryDal.findById(categoryUpdateRequest.getId());

        if (oldCategory == null) {
            return null;
        }

        Category updatedcustomer =modelMapper.map(categoryUpdateRequest,Category.class);
        this.categoryDal.update(updatedcustomer);
        return updatedcustomer;
    }
    public Category updateActive(CategoryActiveRequest categoryActiveRequest) {
        Category oldCategory =  categoryDal.findById(categoryActiveRequest.getId());

        if (oldCategory == null) {
            return null;
        }
        Category updatedcustomer =modelMapper.map(categoryActiveRequest,Category.class);

        updatedcustomer.setName(oldCategory.getName());
        updatedcustomer.setDescription(oldCategory.getDescription());

        this.categoryDal.update(updatedcustomer);
        return updatedcustomer;
    }

    @Override
    public Category deleteCategory(int id) {
        Category category = categoryDal.findById(id);
        if (category == null) {
            return null;
        }
         categoryDal.delete(category);
        return category;
    }
    @Override
    public FindByIdCategoryResponse findById(int id)
    {
        Category category = categoryDal.findById(id);
        if (category == null) {
            return null;
        }
        return modelMapper.map(category, FindByIdCategoryResponse.class);
    }
}
