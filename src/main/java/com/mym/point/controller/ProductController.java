package com.mym.point.controller;

import com.mym.point.dto.ProductDto;
import com.mym.point.entity.ProductEntity;
import com.mym.point.exception.EntityAlreadyExistsException;
import com.mym.point.exception.EntityNotFoundException;
import com.mym.point.exception.InvalidProductException;
import com.mym.point.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping (value = "/products")
    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }

    @GetMapping (value = "/{codeOrNameProduct}")
    public List <ProductDto> getProduct(@PathVariable String codeOrNameProduct) {
        return productService.getProduct(codeOrNameProduct);
    }

    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductDto newProduct) throws EntityAlreadyExistsException,
            InvalidProductException {

       ProductEntity newProductEntity = productService.createProduct(newProduct);
        ProductDto newProductDto = ProductDto.builder()
                        .productId(newProductEntity.getProductId())
                        .code(newProductEntity.getCode())
                        .name(newProductEntity.getName())
                        .price(newProductEntity.getPrice())
                        .unit(newProductEntity.getUnit())
                        .createDate(newProductEntity.getCreateDate())
                        .lastUpdate(newProductEntity.getLastUpdate())
                        .build();
        return newProductDto;
    }

    @PutMapping
    public void updateProduct(@Valid @RequestBody ProductDto productToUpdate) throws EntityNotFoundException,
            InvalidProductException, EntityAlreadyExistsException {
                productService.updateProduct(productToUpdate);
    }

    @DeleteMapping(value = "{id}")
    public void deleteProduct(@PathVariable Long id) throws EntityNotFoundException {
        productService.deleteProduct(id);
    }

}
