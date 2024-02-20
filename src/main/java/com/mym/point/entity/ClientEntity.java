package com.mym.point.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "client")
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    private static final String ID_SEQ = "client_client_id";

    @GeneratedValue(generator = ID_SEQ, strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = ID_SEQ, sequenceName = ID_SEQ, allocationSize = 1)
    @Id
    private Long clientId;

    private String name;
}
