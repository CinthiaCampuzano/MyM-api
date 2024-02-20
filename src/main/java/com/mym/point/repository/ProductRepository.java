package com.mym.point.repository;

import com.mym.point.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT p FROM ProductEntity p WHERE p.code LIKE %:code% OR LOWER(p.name) LIKE LOWER(concat('%', :code, '%'))")
    List<ProductEntity> findAllByCodeOrName(@Param("code") String code);

    @Query(value = "SELECT p FROM ProductEntity p WHERE p.code = :code")
    ProductEntity findAByCode(@Param("code") String code);

    @Query(value = "SELECT p FROM ProductEntity p WHERE p.name = :name")
    Optional<ProductEntity> findAByName(@Param("name") String name);

    boolean existsByCode(String code);
    boolean existsByName(String name);

}


