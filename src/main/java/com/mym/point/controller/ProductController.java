package com.mym.point.controller;

import com.mym.point.dto.ProductDto;
import com.mym.point.exception.EntityAlreadyExistsException;
import com.mym.point.exception.EntityNotFoundException;
import com.mym.point.exception.InvalidProductException;
import com.mym.point.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
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
       return productService.createProduct(newProduct);

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
