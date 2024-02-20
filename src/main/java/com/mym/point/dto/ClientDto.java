package com.mym.point.dto;

import lombok.*;

@Builder
@Data
public class ClientDto {
    private Long clientId;

    private String name;
}
