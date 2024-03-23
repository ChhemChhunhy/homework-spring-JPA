package co.istad.springhomeworkjpa.service.impl;

import co.istad.springhomeworkjpa.dto.CategoryEditRequest;
import co.istad.springhomeworkjpa.dto.CategoryRequest;
import co.istad.springhomeworkjpa.dto.CategoryResponse;
import co.istad.springhomeworkjpa.model.Category;
import co.istad.springhomeworkjpa.repository.CategoryRepository;
import co.istad.springhomeworkjpa.service.CategoryService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void deleteCategoryById(Integer id) {
       if (!categoryRepository.existsById(id)){
              throw new ResponseStatusException(
                      HttpStatus.NOT_FOUND,
                      "Category has not been found"
              );
       }
       categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse editCategoryById(Integer id, CategoryEditRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Category has not been found!"
        ));
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
        return this.findCategoryById(id);
    }

    @Override
    public void createNewCategory(CategoryRequest request) {

        if(categoryRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already exists try again!!!"
            );
        }
         Category category = new Category();
         category.setName(request.name());
         category.setDescription(request.description());
         categoryRepository.save(category);
    }

    //find all categories
    @Override
    public List<CategoryResponse> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryResponse(category.getName(),category.getDescription()))
                .toList();
    }

    //find category by id
    @Override
    public CategoryResponse findCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Category has not been found"
        ));
        return new CategoryResponse(category.getName(),category.getDescription());
    }
}
