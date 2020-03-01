package com.cosmin.tweets.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hashtag", schema = "tweets")
public class Hashtag extends AuditEntity {

    @Id
    private String id;

    @Column
    private Integer count;

    public void incrementCount() {
        this.count += 1;
    }

}
