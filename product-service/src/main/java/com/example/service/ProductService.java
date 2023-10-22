package com.example.service;

import com.example.dto.ProductRequest;
import com.example.dto.ProductResponse;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    //constructor injection without autowired!! - replaced by @RequiredArgsConstructor
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        //logger from lombok
     //   log.info("Product "+product.getId()+" is saved!");
        log.info("Product {} is saved", product.getId()); //using placeholder
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        //converting products to product response objects
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
