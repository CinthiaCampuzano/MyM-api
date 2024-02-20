package com.mym.point.dto;


import com.mym.point.enums.EUnit;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class ProductDto {

    private Long productId;

    private String code;

    private String name;

    @Digits(integer = 15, fraction = 2, message = "Formato de precio invalio, ingresar solo 2 decimales")
    @PositiveOrZero(message = "El precio debe ser un valor positivo o cero")
    private BigDecimal price;

    private EUnit unit;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdate;

}
