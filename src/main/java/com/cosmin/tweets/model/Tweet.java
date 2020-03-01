package com.cosmin.tweets.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tweet", schema = "tweets")
public class Tweet extends AuditEntity {

    @Id
    private Long id;

    @Column
    private String userName;

    @Column
    private String text;

    @Column
    private String location;

    @Column
    private Boolean validation;

}
