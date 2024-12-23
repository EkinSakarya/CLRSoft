package com.RentACar.CLRSoft.category.categoryDal;

import com.RentACar.CLRSoft.category.entity.Category;

import java.util.List;

public interface ICategoryDal {

    List<Category> findAll();

    void add(Category category);

    void update(Category category);

    void delete(Category category);

    Category findById(int id);

}
