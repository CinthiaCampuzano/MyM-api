package com.mym.point.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
public class Auditable {

    @LastModifiedDate
    private LocalDateTime lastUpdate;

    @CreatedDate
    private LocalDateTime createDate;
}
