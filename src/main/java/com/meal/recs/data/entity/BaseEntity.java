package com.meal.recs.data.entity;

import com.meal.recs.model.ModelStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: gardiary
 * Date: 27/12/21, 14.02
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable=false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ModelStatus status = ModelStatus.ACTIVE;
}
