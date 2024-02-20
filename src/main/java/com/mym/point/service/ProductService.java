package com.mym.point.service;

import com.mym.point.dto.ProductDto;
import com.mym.point.entity.ProductEntity;
import com.mym.point.enums.EUnit;
import com.mym.point.exception.EntityAlreadyExistsException;
import com.mym.point.exception.EntityNotFoundException;
import com.mym.point.exception.InvalidProductException;
import com.mym.point.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getProducts(){
        List<ProductEntity> productEntityList = productRepository.findAll();

        List<ProductDto> productDtoList = productEntityList.stream().
                map(product -> ProductDto.builder()
                        .productId(product.getProductId())
                        .code(product.getCode())
                        .name(product.getName())
                        .price(product.getPrice())
                        .unit(product.getUnit())
                        .lastUpdate(product.getLastUpdate()).build()
                ).toList();
         return productDtoList;
    }

    public List<ProductDto> getProduct(String codeOrNameProduct){
        List<ProductEntity> productEntityList = productRepository.findAllByCodeOrName(codeOrNameProduct);

        List<ProductDto> productDtoList = productEntityList.stream()
                .map(product -> ProductDto.builder()
                        .productId(product.getProductId())
                        .code(product.getCode())
                        .name(product.getName())
                        .price(product.getPrice())
                        .unit(product.getUnit())
                        .lastUpdate(product.getLastUpdate())
                        .build()
                ).toList();
        return productDtoList;
    }

    public ProductEntity createProduct(ProductDto newProduct) throws EntityAlreadyExistsException, InvalidProductException {

        if (newProduct.getCode() != null && productRepository.
                existsByCode(newProduct.getCode())) {
            throw new EntityAlreadyExistsException("Ya existe un producto con el mismo código de barras");
        }

        if (productRepository.existsByName(newProduct.getName())) {
            throw new EntityAlreadyExistsException("Ya existe un producto con el mismo nombre");
        }

        if (EUnit.KG == newProduct.getUnit() && newProduct.getCode() != null) {
            throw new InvalidProductException("No se puede cargar código de producto cuando es del tipo KG");
        }

        ProductEntity productEntityNew = ProductEntity.builder()
                .code(newProduct.getCode())
                .name(newProduct.getName())
                .price(newProduct.getPrice())
                .unit(newProduct.getUnit())
                .build();

        return productRepository.save(productEntityNew);

    }

    public void updateProduct(ProductDto productToUpdate) throws EntityNotFoundException, InvalidProductException, EntityAlreadyExistsException {

        if (EUnit.KG == productToUpdate.getUnit() && productToUpdate.getCode() != null){
            throw new InvalidProductException("No se puede cargar código de producto cuando es del tipo KG");
        }

       boolean productExists =  productRepository.existsById(productToUpdate.getProductId());

        if (!productExists){
            throw new EntityNotFoundException("No existe este producto");
        }

        ProductEntity newProduct = productRepository.findById(productToUpdate.getProductId()).get();

        newProduct.setCode(productToUpdate.getCode());
        newProduct.setName(productToUpdate.getName());
        newProduct.setPrice(productToUpdate.getPrice());
        newProduct.setUnit(productToUpdate.getUnit());

        try {
            productRepository.save(newProduct);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyExistsException("Ya existe un producto con el mismo nombre");
        }

    }

    public void deleteProduct(Long idProduct) throws EntityNotFoundException {
        boolean productExists =  productRepository.existsById(idProduct);

        if (!productExists) {
            throw new EntityNotFoundException("No se encontro el producto");
        }
        productRepository.deleteById(idProduct);

    }

}
