package com.mym.point.entity;

import com.mym.point.enums.EUnit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "product")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity extends Auditable {

    private static final String ID_SEQ = "product_product_id";

    @GeneratedValue(generator = ID_SEQ, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    @Id
    private Long productId;

    private String code;

    private String name;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private EUnit unit;

}
