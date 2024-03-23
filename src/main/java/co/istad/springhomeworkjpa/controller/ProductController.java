package co.istad.springhomeworkjpa.controller;

import co.istad.springhomeworkjpa.dto.ProductCreateRequest;
import co.istad.springhomeworkjpa.dto.ProductEditRequest;
import co.istad.springhomeworkjpa.dto.ProductResponse;
import co.istad.springhomeworkjpa.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    List<ProductResponse> findAllProducts(@RequestParam (required = false,defaultValue = "") String name,
                                          @RequestParam (required = false,defaultValue = "true" ) Boolean status){
        return productService.findAllProducts(name,status);
    }

    @GetMapping("/{id}")
    ProductResponse findProductById(@PathVariable Integer id){
        return productService.findProductById(id);
    }

    @GetMapping("uuid/{uuid}")
    ProductResponse findProductByUuid(@PathVariable String uuid){
        return productService.findProductByUuid(uuid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNewProduct(@Valid @RequestBody ProductCreateRequest request){
        productService.createNewProduct(request);
    }

    @PutMapping("{id}")
    ProductResponse editProductById(@PathVariable Integer id,@Valid @RequestBody ProductEditRequest request){
        return productService.editProductById(id,request);
    }

    @PutMapping("uuid/{uuid}")
    ProductResponse editProductByUuid(@PathVariable String uuid,@Valid @RequestBody ProductEditRequest request){
        return productService.editProductByUuid(uuid, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void deleteProductById(@PathVariable Integer id){
        productService.deleteProductById(id);
    }


}
