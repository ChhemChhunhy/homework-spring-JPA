package co.istad.springhomeworkjpa.service;

import co.istad.springhomeworkjpa.dto.ProductCreateRequest;
import co.istad.springhomeworkjpa.dto.ProductEditRequest;
import co.istad.springhomeworkjpa.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    void deleteProductById(Integer id);
    ProductResponse editProductById(Integer id, ProductEditRequest request);
    ProductResponse editProductByUuid(String uuid,ProductEditRequest request);
    void createNewProduct(ProductCreateRequest request);
    ProductResponse findProductById(Integer id);
    ProductResponse findProductByUuid(String uuid);
    List<ProductResponse> findAllProducts(String name,Boolean status);
}
