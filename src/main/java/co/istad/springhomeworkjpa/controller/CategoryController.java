package co.istad.springhomeworkjpa.controller;

import co.istad.springhomeworkjpa.dto.CategoryEditRequest;
import co.istad.springhomeworkjpa.dto.CategoryRequest;
import co.istad.springhomeworkjpa.dto.CategoryResponse;
import co.istad.springhomeworkjpa.service.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;


  //find all categories
  @GetMapping
    List<CategoryResponse> findAllCategories(){
      return categoryService.findAllCategories();
  }

  //find category by id
  @GetMapping("/{id}")
  CategoryResponse findCategoryById(@PathVariable Integer id){
    return  categoryService.findCategoryById(id);
  }

  //post category
    @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  void createNewCategory(@Valid @RequestBody CategoryRequest request){
     categoryService.createNewCategory(request);
  }

   @PutMapping("/{id}")
  CategoryResponse editCategoryById(@PathVariable Integer id,@Valid @RequestBody CategoryEditRequest request){
    return categoryService.editCategoryById(id,request);
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteCategoryById(@PathVariable Integer id){
       categoryService.deleteCategoryById(id);
   }

}
