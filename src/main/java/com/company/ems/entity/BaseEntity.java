package com.company.ems.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public abstract class BaseEntity {
    @CreatedBy private String createdBy;
    @CreatedDate private LocalDateTime createdOn;
    @LastModifiedBy private String modifiedBy;
    @LastModifiedDate private LocalDateTime modifiedOn;
    private boolean active = true;
}