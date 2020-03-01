package com.cosmin.tweets.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

    @Column(name = "insert_timestamp", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "update_timestamp")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
