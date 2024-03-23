package co.istad.springhomeworkjpa.service;


import co.istad.springhomeworkjpa.dto.CategoryEditRequest;
import co.istad.springhomeworkjpa.dto.CategoryRequest;
import co.istad.springhomeworkjpa.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    void deleteCategoryById(Integer id);
    CategoryResponse editCategoryById(Integer id,CategoryEditRequest request);
    void createNewCategory(CategoryRequest request);
    List<CategoryResponse> findAllCategories();
    CategoryResponse findCategoryById(Integer id);
}
