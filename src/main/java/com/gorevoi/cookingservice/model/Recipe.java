package com.gorevoi.cookingservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Recipe extends BaseModel {
    @Column
    private String title;

    @Column
    private String link;

    @Column
    private String description;

    @Column
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id",insertable = false, updatable = false)
//    @Setter(AccessLevel.PRIVATE)
    @JsonIgnore
    private UserOfService user;

}
