package co.istad.springhomeworkjpa.service.impl;

import co.istad.springhomeworkjpa.dto.CategoryResponse;
import co.istad.springhomeworkjpa.dto.ProductCreateRequest;
import co.istad.springhomeworkjpa.dto.ProductEditRequest;
import co.istad.springhomeworkjpa.dto.ProductResponse;
import co.istad.springhomeworkjpa.model.Category;
import co.istad.springhomeworkjpa.model.Product;
import co.istad.springhomeworkjpa.repository.ProductRepository;
import co.istad.springhomeworkjpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;



    @Override
    public void deleteProductById(Integer id) {
        if(!productRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product"+id+" has not been found"
            );
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse editProductById(Integer id, ProductEditRequest request) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Product has not been found!"
                )
        );
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        productRepository.save(product);
        return this.findProductById(id);
    }

    @Override
    public ProductResponse editProductByUuid(String uuid, ProductEditRequest request) {
        Product product = productRepository.findByUuid(uuid).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.CONFLICT
                                ,"Product has not been found "
                )
        );
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQty(request.qty());
        productRepository.save(product);
        return this.findProductByUuid(uuid);
    }

    @Override
    public void createNewProduct(ProductCreateRequest request) {
        if(productRepository.existsByName(request.name())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Product already exists!"
            );
        }
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setUuid(UUID.randomUUID().toString());
        product.setImportDate(LocalDateTime.now());
        product.setStatus(true);
        product.setQty(request.qty());
        productRepository.save(product);

    }

    @Override
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findByIdAndStatus(id, true)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        return new ProductResponse(
                product.getUuid(),
                product.getName(),
                product.getPrice(),
                product.getQty()
        );
    }

    @Override
    public ProductResponse findProductByUuid(String uuid) {
        Product product = productRepository.findByUuidAndStatus(uuid,true)
                .orElseThrow(
                        ()-> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Product has not been found"
                        )
                );

        return new ProductResponse(
                product.getUuid(),
                product.getName(),
                product.getPrice(),
                product.getQty()
        );
    }

    @Override
    public List<ProductResponse> findAllProducts(String name,Boolean status) {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .filter(product -> product.getName().toLowerCase()
                        .contains(name.toLowerCase()) && product.getStatus().equals(status))
                .map(product -> new ProductResponse(
                        product.getUuid(),
                        product.getName(),
                        product.getPrice(),
                        product.getQty()
                )).toList();
    }
}
